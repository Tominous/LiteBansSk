package me.pugabyte.litebanssk.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import me.pugabyte.litebanssk.LiteBansSk;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import java.util.concurrent.TimeUnit;

public class EffCreateEntry extends Effect {
	public static String syntax = "[(1¦silently)] [(2¦ip)] (4¦ban|8¦mute|12¦kick|16¦warn) %string% " +
			"[with reason %-string%] [for duration %-timespan%] [on server %-string%] [as %-string%]";

	private Expression<String> punished;
	private Expression<String> reason;
	private Expression<Timespan> duration;
	private Expression<String> server;
	private Expression<String> executor;
	private int parseMark;

	static {
		Skript.registerEffect(EffCreateEntry.class, syntax);
	}

	@Override
	protected void execute(Event event) {
		String command;

		int action = (parseMark / 4) - 1;
		switch (action) {
			case 0:
				command = "ban";
				break;
			case 1:
				command = "mute";
				break;
			case 2:
				command = "kick";
				break;
			case 3:
				command = "warn";
				break;
			default:
				return;
		}

		// IP
		// Ignore for kick and warn
		if (action < 2) {
			if ((parseMark & 2) == 2) {
				command = "ip" + command;
			}
		}

		// Silently
		if ((parseMark & 1) == 1) {
			command += " -s";
		}

		// Punished
		if (punished == null) {
			return;
		}
		command += " " + punished.getSingle(event);

		// Duration
		// Ignore for kick and warn
		if (action < 2) {
			if (duration != null) {
				long millis = duration.getSingle(event).getTicks_i() * 50;
				int days    = (int) ((millis / (1000*60*60*24)) % 365);
				int hours   = (int) ((millis / (1000*60*60)) % 24);
				int minutes = (int) ((millis / (1000*60)) % 60);
				int seconds = (int) (millis / 1000) % 60 ;

				String durationString = "";
				if (days != 0) durationString += days + "d";
				if (hours != 0) durationString += hours + "h";
				if (minutes != 0) durationString += minutes + "m";
				if (seconds != 0) durationString += seconds + "s";

				LiteBansSk.getInstance().getLogger().info(durationString);
				command += " " + durationString;
			}
		}

		// Reason
		if (reason != null) {
			command += " " + reason.getSingle(event);
		}

		// Server
		if (server != null) {
			command += " server:" + server.getSingle(event);
		}

		// Executor
		if (executor != null) {
			command += " --sender=" + executor.getSingle(event);
		}

		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		punished = (Expression<String>) expr[0];
		reason = (Expression<String>) expr[1];
		duration = (Expression<Timespan>) expr[2];
		server = (Expression<String>) expr[3];
		executor = (Expression<String>) expr[4];
		parseMark = parseResult.mark;
		return true;
	}

	@Override
	public String toString(Event event, boolean debug) {
		return syntax;
	}

}
