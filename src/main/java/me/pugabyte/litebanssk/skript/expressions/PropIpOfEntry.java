package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import litebans.api.Entry;

public class PropIpOfEntry extends SimplePropertyExpression<Entry, String> {

	static {
		PropertyExpression.register(PropIpOfEntry.class, String.class, "ip", "entry");
	}

	@Override
	public String convert(final Entry entry) {
		String ip = entry.getIp();
		if (ip == null || ip.startsWith("#")) {
			return null;
		}
		return ip;
	}

	@Override
	public String getPropertyName() {
		return "ip";
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}
