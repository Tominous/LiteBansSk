package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.pugabyte.litebanssk.skript.events.BroadcastEvent;
import org.bukkit.event.Event;

public class ExprBroadcast extends SimpleExpression<BroadcastEvent> {

	public static String syntax = "[litebans] event(-| )broadcast";

	static {
		Skript.registerExpression(ExprBroadcast.class, BroadcastEvent.class, ExpressionType.SIMPLE, syntax);
	}

	@Override
	protected BroadcastEvent[] get(Event event) {
		return new BroadcastEvent[]{(BroadcastEvent) event};
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (ScriptLoader.isCurrentEvent(BroadcastEvent.class)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends BroadcastEvent> getReturnType() {
		return BroadcastEvent.class;
	}

	@Override
	public String toString(Event event, boolean debug) {
		return syntax;
	}
}
