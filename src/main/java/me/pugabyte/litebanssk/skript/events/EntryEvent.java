package me.pugabyte.litebanssk.skript.events;

import litebans.api.Entry;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EntryEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Entry entry;

	public EntryEvent(final Entry entry) {
		this.entry = entry;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Entry getEntry() {
		return entry;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
