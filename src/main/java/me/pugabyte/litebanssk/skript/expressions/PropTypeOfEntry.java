package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import litebans.api.Entry;

public class PropTypeOfEntry extends SimplePropertyExpression<Entry, String> {

	static {
		PropertyExpression.register(PropTypeOfEntry.class, String.class, "type", "entry");
	}

	@Override
	public String convert(final Entry entry) {
		return entry.getType();
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
