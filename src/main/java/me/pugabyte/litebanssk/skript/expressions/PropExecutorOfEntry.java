package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import litebans.api.Entry;

public class PropExecutorOfEntry extends SimplePropertyExpression<Entry, String> {

	static {
		PropertyExpression.register(PropExecutorOfEntry.class, String.class, "executor", "entry");
	}

	@Override
	public String convert(final Entry entry) {
		return entry.getExecutorUUID();
	}

	@Override
	public String getPropertyName() {
		return "executor";
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}
