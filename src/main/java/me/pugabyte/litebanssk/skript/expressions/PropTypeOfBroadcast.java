package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.pugabyte.litebanssk.skript.events.BroadcastEvent;

public class PropTypeOfBroadcast extends SimplePropertyExpression<BroadcastEvent, String> {

	static {
		PropertyExpression.register(PropTypeOfBroadcast.class, String.class, "type", "broadcast");
	}

	@Override
	public String convert(final BroadcastEvent event) {
		return event.getType();
	}

	@Override
	public String getPropertyName() {
		return "type";
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}
