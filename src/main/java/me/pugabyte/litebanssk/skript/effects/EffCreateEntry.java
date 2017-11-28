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

	public static String syntax = "(0¦ban|1¦silently ban|2¦mute|3¦silently mute|4¦kick|5¦silently kick|6¦warn|7¦silently warn) %string% " +
			"[with reason %-string%] [for duration %-timespan%] [as %-string%]";

	private Expression<String> punished;
	private Expression<String> reason;
	private Expression<Timespan> duration;
	private Expression<String> executor;
	private int action;

	static {
		Skript.registerEffect(EffCreateEntry.class, syntax);
	}

	@Override
	protected void execute(Event event) {
		String command;
		switch (action) {
			case 0:
				command = "ban";
				break;
			case 1:
				command = "ban -s";
				break;
			case 2:
				command = "mute";
				break;
			case 3:
				command = "mute -s";
				break;
			case 4:
				command = "kick";
				break;
			case 5:
				command = "kick -s";
				break;
			case 6:
				command = "warn";
				break;
			case 7:
				command = "warn -s";
				break;
			default:
				return;
		}

		// Punished
		if (punished == null) {
			return;
		}
		command += " " + punished.getSingle(event);

		// Duration
		// Ignore for kick and warn
		if (action < 4) {
			if (duration != null) {
				command += " " + (duration.getSingle(event).getTicks_i() / 20) + "s";
			}
		}

		// Reason
		if (reason != null) {
			command += " " + reason.getSingle(event);
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
		executor = (Expression<String>) expr[3];
		action = parseResult.mark;
		return true;
	}

	@Override
	public String toString(Event event, boolean debug) {
		return syntax;
	}

}
