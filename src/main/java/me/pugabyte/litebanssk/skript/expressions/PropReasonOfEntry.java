package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import litebans.api.Entry;

public class PropReasonOfEntry extends SimplePropertyExpression<Entry, String> {

	static {
		PropertyExpression.register(PropReasonOfEntry.class, String.class, "reason", "entry");
	}

	@Override
	public String convert(final Entry entry) {
		return entry.getReason();
	}

	@Override
	public String getPropertyName() {
		return "reason";
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}
