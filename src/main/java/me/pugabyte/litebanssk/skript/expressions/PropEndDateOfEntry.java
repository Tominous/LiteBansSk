package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import litebans.api.Entry;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PropEndDateOfEntry extends SimplePropertyExpression<Entry, LocalDateTime> {

	static {
		PropertyExpression.register(PropEndDateOfEntry.class, LocalDateTime.class, "end date", "entry");
	}

	@Override
	public LocalDateTime convert(final Entry entry) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(entry.getDateEnd()), ZoneId.systemDefault());
	}

	@Override
	public String getPropertyName() {
		return "end date";
	}

	@Override
	public Class<? extends LocalDateTime> getReturnType() {
		return LocalDateTime.class;
	}
}
