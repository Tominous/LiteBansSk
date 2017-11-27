package me.pugabyte.litebanssk.skript.events;

import litebans.api.Entry;
import org.bukkit.event.HandlerList;

public class KickEvent extends EntryEvent {

	public KickEvent(Entry entry) {
		super(entry);
	}

	public String getType() {
		return "kick";
	}

	@Override
	public HandlerList getHandlers() {
		return null;
	}
}
