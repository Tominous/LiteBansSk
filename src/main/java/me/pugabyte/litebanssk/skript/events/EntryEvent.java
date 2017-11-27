package me.pugabyte.litebanssk.skript.events;

import litebans.api.Entry;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EntryEvent extends Event {
	private final Entry entry;

	public EntryEvent(Entry entry) {
		this.entry = entry;
	}

	public Entry getEntry() {
		return entry;
	}

	@Override
	public HandlerList getHandlers() {
		return null;
	}
}
