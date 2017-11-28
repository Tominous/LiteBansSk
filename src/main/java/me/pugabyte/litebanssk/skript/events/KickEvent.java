package me.pugabyte.litebanssk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import litebans.api.Entry;

public class KickEvent extends EntryEvent {

	static {
		Skript.registerEvent("litebans kick", SimpleEvent.class, KickEvent.class, "[new] litebans kick");
		EventValues.registerEventValue(KickEvent.class, Entry.class, new Getter<Entry, KickEvent>() {
			@Override
			public Entry get(KickEvent event) {
				return event.getEntry();
			}
		}, 0);
	}

	public KickEvent(final Entry entry) {
		super(entry);
	}
}
