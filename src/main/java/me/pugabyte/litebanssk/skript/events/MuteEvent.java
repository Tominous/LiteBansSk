package me.pugabyte.litebanssk.skript.events;

import litebans.api.Entry;
import org.bukkit.event.HandlerList;

public class MuteEvent extends EntryEvent {

	public MuteEvent(Entry entry) {
		super(entry);
	}

	public String getType() {
		return "mute";
	}

	@Override
	public HandlerList getHandlers() {
		return null;
	}
}
