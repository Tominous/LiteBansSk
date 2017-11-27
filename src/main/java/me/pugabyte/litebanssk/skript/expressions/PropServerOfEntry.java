package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import litebans.api.Entry;

public class PropServerOfEntry extends SimplePropertyExpression<Entry, String> {

	static {
		PropertyExpression.register(PropServerOfEntry.class, String.class, "server", "entry");
	}

	@Override
	public String convert(final Entry entry) {
		return entry.getServerScope();
	}

	@Override
	public String getPropertyName() {
		return "server";
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}
