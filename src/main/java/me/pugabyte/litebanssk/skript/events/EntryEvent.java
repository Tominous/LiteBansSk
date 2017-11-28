package me.pugabyte.litebanssk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import litebans.api.Entry;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EntryEvent extends Event {

	static {
		Skript.registerEvent("litebans entry", SimpleEvent.class, EntryEvent.class, "[on] [new] litebans entry");
		EventValues.registerEventValue(EntryEvent.class, Entry.class, new Getter<Entry, EntryEvent>() {
			@Override
			public Entry get(EntryEvent event) {
				return event.getEntry();
			}
		}, 0);
	}

	private static final HandlerList handlers = new HandlerList();
	private Entry entry;

	public EntryEvent(final Entry entry) {
		this.entry = entry;
	}

	public Entry getEntry() {
		return entry;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
