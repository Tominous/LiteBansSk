package me.pugabyte.litebanssk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BroadcastEvent extends Event {

	static {
		Skript.registerEvent("litebans broadcast", SimpleEvent.class, BroadcastEvent.class, "[on] litebans broadcast");
		EventValues.registerEventValue(BroadcastEvent.class, String.class, new Getter<String, BroadcastEvent>() {
			@Override
			public String get(BroadcastEvent event) {
				return event.getMessage();
			}
		}, 0);
	}

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
