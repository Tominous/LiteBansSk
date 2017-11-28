package me.pugabyte.litebanssk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import litebans.api.Entry;

public class MuteEvent extends EntryEvent {

	static {
		Skript.registerEvent("litebans mute", SimpleEvent.class, MuteEvent.class, "[new] litebans mute");
		EventValues.registerEventValue(MuteEvent.class, Entry.class, new Getter<Entry, MuteEvent>() {
			@Override
			public Entry get(MuteEvent event) {
				return event.getEntry();
			}
		}, 0);
	}

	public MuteEvent(final Entry entry) {
		super(entry);
	}
}
