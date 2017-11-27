package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import litebans.api.Entry;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PropStartDateOfEntry extends SimplePropertyExpression<Entry, LocalDateTime> {

	static {
		PropertyExpression.register(PropStartDateOfEntry.class, LocalDateTime.class, "start date", "entry");
	}

	@Override
	public LocalDateTime convert(final Entry entry) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(entry.getDateStart()), ZoneId.systemDefault());
	}

	@Override
	public String getPropertyName() {
		return "start date";
	}

	@Override
	public Class<? extends LocalDateTime> getReturnType() {
		return LocalDateTime.class;
	}
}
