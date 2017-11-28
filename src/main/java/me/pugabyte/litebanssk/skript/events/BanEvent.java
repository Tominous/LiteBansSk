package me.pugabyte.litebanssk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import litebans.api.Entry;

public class BanEvent extends EntryEvent {

	static {
		Skript.registerEvent("litebans ban", SimpleEvent.class, BanEvent.class, "[on] [new] litebans ban");
		EventValues.registerEventValue(BanEvent.class, Entry.class, new Getter<Entry, BanEvent>() {
			@Override
			public Entry get(BanEvent event) {
				return event.getEntry();
			}
		}, 0);
	}

	public BanEvent(final Entry entry) {
		super(entry);
	}
}
