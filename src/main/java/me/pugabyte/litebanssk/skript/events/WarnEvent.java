package me.pugabyte.litebanssk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import litebans.api.Entry;

public class WarnEvent extends EntryEvent {

	static {
		Skript.registerEvent("litebans warn", SimpleEvent.class, WarnEvent.class, "[new] litebans warn");
		EventValues.registerEventValue(WarnEvent.class, Entry.class, new Getter<Entry, WarnEvent>() {
			@Override
			public Entry get(WarnEvent event) {
				return event.getEntry();
			}
		}, 0);
	}

	public WarnEvent(final Entry entry) {
		super(entry);
	}
}
