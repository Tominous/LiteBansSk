package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Timespan;
import litebans.api.Entry;

public class PropDurationOfEntry extends SimplePropertyExpression<Entry, Timespan> {

	static {
		PropertyExpression.register(PropDurationOfEntry.class, Timespan.class, "duration", "entry");
	}

	@Override
	public Timespan convert(final Entry entry) {
		int ticks = (int) (entry.getDuration() / 50);
		return Timespan.fromTicks(ticks);
	}

	@Override
	public String getPropertyName() {
		return "duration";
	}

	@Override
	public Class<? extends Timespan> getReturnType() {
		return Timespan.class;
	}
}
