package me.pugabyte.litebanssk.skript.events;

import litebans.api.Entry;
import org.bukkit.event.HandlerList;

public class BanEvent extends EntryEvent {

	public BanEvent(Entry entry) {
		super(entry);
	}

	public String getType() {
		return "ban";
	}

	@Override
	public HandlerList getHandlers() {
		return null;
	}
}
