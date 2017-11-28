package me.pugabyte.litebanssk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import litebans.api.Entry;

public class KickEvent extends EntryEvent {

	static {
		Skript.registerEvent("litebans kick", SimpleEvent.class, KickEvent.class, "[new] litebans kick");
	}

	public KickEvent(final Entry entry) {
		super(entry);
	}
}
