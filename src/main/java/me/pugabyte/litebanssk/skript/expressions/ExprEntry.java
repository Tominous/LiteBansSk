package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import litebans.api.Entry;
import me.pugabyte.litebanssk.skript.events.*;
import org.bukkit.event.Event;

public class ExprEntry extends SimpleExpression<Entry> {

	public static String syntax = "event(-| )(ban|mute|kick|warn|entry)";

	static {
		Skript.registerExpression(ExprEntry.class, Entry.class, ExpressionType.SIMPLE, syntax);
	}

	@Override
	protected Entry[] get(Event event) {
		return new Entry[] { ((EntryEvent) event).getEntry() };
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		if (!(ScriptLoader.isCurrentEvent(EntryEvent.class) ||
				ScriptLoader.isCurrentEvent(BanEvent.class) ||
				ScriptLoader.isCurrentEvent(MuteEvent.class) ||
				ScriptLoader.isCurrentEvent(KickEvent.class) ||
				ScriptLoader.isCurrentEvent(WarnEvent.class))) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Entry> getReturnType() {
		return Entry.class;
	}

	@Override
	public String toString(Event event, boolean debug) {
		return syntax;
	}
}
