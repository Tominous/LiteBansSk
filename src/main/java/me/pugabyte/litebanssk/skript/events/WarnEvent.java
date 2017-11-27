package me.pugabyte.litebanssk.skript.events;

import litebans.api.Entry;
import org.bukkit.event.HandlerList;

public class WarnEvent extends EntryEvent {

	public WarnEvent(Entry entry) {
		super(entry);
	}

	public String getType() {
		return "warn";
	}

	@Override
	public HandlerList getHandlers() {
		return null;
	}
}
