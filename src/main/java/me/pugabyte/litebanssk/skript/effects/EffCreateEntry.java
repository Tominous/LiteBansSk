package me.pugabyte.litebanssk.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

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
				command += " " + (duration.getSingle(event).getTicks_i() / 20) + "s";
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
