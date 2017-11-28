package me.pugabyte.litebanssk.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

public class EffCreateEntry extends Effect {

	public static String syntax = "[silently] (0¦ban|1¦mute|2¦kick|3¦warn) %string% " +
			"[with reason %-string%] [for duration (%-string%|%-timespan%)] [as %-string%]";

	private Expression<String> punished;
	private Expression<OfflinePlayer> punished2;
	private Expression<String> reason;
	private Expression<String> durationString;
	private Expression<Timespan> durationTimespan;
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
				command = "mute";
				break;
			case 2:
				command = "kick";
				break;
			case 3:
				command = "warn";
				break;
			default:
				Bukkit.broadcastMessage("command null");
				return;
		}

		// Punished
		if (punished == null || punished.getSingle(event) == null) {
			return;
		}
		command += " " + punished.getSingle(event);

		// Duration
		String timespan = null;
		if (durationString != null && durationString.getSingle(event) != null) {
			timespan = durationString.getSingle(event);
		} else if (durationTimespan != null && durationTimespan.getSingle(event) != null) {
			timespan = ((durationTimespan.getSingle(event).getTicks_i() * 20) * 60) + "h";
		}
		if (timespan != null) {
			command += " " + timespan;
		}

		// Reason
		if (reason.getSingle(event) != null) {
			command += " " + reason.getSingle(event);
		}

		// Executor
		if (executor != null && executor.getSingle(event) != null) {
			command += " --sender=" + executor.getSingle(event);
		}

		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		punished = (Expression<String>) expr[0];
		reason = (Expression<String>) expr[1];
		durationString = (Expression<String>) expr[2];
		durationTimespan = (Expression<Timespan>) expr[3];
		executor = (Expression<String>) expr[4];
		action = parseResult.mark;
		return true;
	}

	@Override
	public String toString(Event event, boolean debug) {
		return syntax;
	}

}