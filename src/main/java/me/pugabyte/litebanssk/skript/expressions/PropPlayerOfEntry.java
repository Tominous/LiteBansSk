package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import litebans.api.Entry;

public class PropPlayerOfEntry extends SimplePropertyExpression<Entry, String> {

	static {
		PropertyExpression.register(PropPlayerOfEntry.class, String.class, "player", "entry");
	}

	@Override
	public String convert(final Entry entry) {
		return entry.getUuid();
	}

	@Override
	public String getPropertyName() {
		return "player";
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}
