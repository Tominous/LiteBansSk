package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import litebans.api.Entry;

public class PropOriginServerOfEntry extends SimplePropertyExpression<Entry, String> {

	static {
		PropertyExpression.register(PropOriginServerOfEntry.class, String.class, "origin server", "entry");
	}

	@Override
	public String convert(final Entry entry) {
		return entry.getServerOrigin();
	}

	@Override
	public String getPropertyName() {
		return "origin server";
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}
