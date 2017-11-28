package me.pugabyte.litebanssk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import litebans.api.Entry;

public class MuteEvent extends EntryEvent {

	static {
		Skript.registerEvent("litebans mute", SimpleEvent.class, MuteEvent.class, "[new] litebans mute");
	}

	public MuteEvent(final Entry entry) {
		super(entry);
	}
}
