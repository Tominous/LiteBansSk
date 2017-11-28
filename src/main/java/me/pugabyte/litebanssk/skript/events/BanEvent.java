package me.pugabyte.litebanssk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import litebans.api.Entry;

public class BanEvent extends EntryEvent {

	static {
		Skript.registerEvent("litebans ban", SimpleEvent.class, BanEvent.class, "[new] litebans ban");
	}

	public BanEvent(final Entry entry) {
		super(entry);
	}
}
