package me.pugabyte.litebanssk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import litebans.api.Entry;

public class WarnEvent extends EntryEvent {

	static {
		Skript.registerEvent("litebans warn", SimpleEvent.class, WarnEvent.class, "[new] litebans warn");
	}

	public WarnEvent(final Entry entry) {
		super(entry);
	}
}
