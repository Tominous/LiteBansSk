package me.pugabyte.litebanssk.skript.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BroadcastEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private String message;
	private String type;

	public BroadcastEvent(final String message, final String type) {
		this.message = message;
		this.type = type;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
