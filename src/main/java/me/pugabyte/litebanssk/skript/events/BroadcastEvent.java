package me.pugabyte.litebanssk.skript.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BroadcastEvent extends Event {

	private final String message;
	private final String type;

	public BroadcastEvent(String message, String type) {
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}

	@Override
	public HandlerList getHandlers() {
		return null;
	}
}
