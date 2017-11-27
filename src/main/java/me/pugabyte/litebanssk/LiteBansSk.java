package me.pugabyte.litebanssk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import litebans.api.Entry;
import litebans.api.Events;
import me.pugabyte.litebanssk.skript.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.awt.*;
import java.io.IOException;


public class LiteBansSk extends JavaPlugin {
	private static LiteBansSk instance;
	private static SkriptAddon addonInstance;
	private Events.Listener newEntryListener;
	private Events.Listener broadcastListener;

	public LiteBansSk() {
		if (instance == null) {
			instance = this;
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public void onEnable() {
		try{
			register();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		Events.get().unregister(newEntryListener);
		Events.get().unregister(broadcastListener);
	}

	public static SkriptAddon getAddonInstance() {
		if (addonInstance == null) {
			addonInstance = Skript.registerAddon(getInstance());
		}
		return addonInstance;
	}

	public static LiteBansSk getInstance() {
		if (instance == null) {
			throw new IllegalStateException();
		}
		return instance;
	}

	public void register() throws IOException {
		newEntryListener = new Events.Listener() {
			@Override
			public void entryAdded(Entry entry) {
				if (entry.getType().equals("ban")) {
					Bukkit.getPluginManager().callEvent(new BanEvent(entry));
				} else if (entry.getType().equals("mute")) {
					Bukkit.getPluginManager().callEvent(new MuteEvent(entry));
				} else if (entry.getType().equals("kick")) {
					Bukkit.getPluginManager().callEvent(new KickEvent(entry));
				} else if (entry.getType().equals("warn")) {
					Bukkit.getPluginManager().callEvent(new WarnEvent(entry));
				}
			}
		};

		broadcastListener = new Events.Listener() {
			@Override
			public void broadcastSent(String message, @Nullable String type) {
				Bukkit.getPluginManager().callEvent(new BroadcastEvent(message, type));
			}
		};

		Events.get().register(newEntryListener);
		Events.get().register(broadcastListener);

		Skript.registerEvent("[on] [new] litebans entry:", SimpleEvent.class, EntryEvent.class);
		Skript.registerEvent("[on] [new] litebans ban:", SimpleEvent.class, BanEvent.class);
		Skript.registerEvent("[on] [new] litebans mute:", SimpleEvent.class, MuteEvent.class);
		Skript.registerEvent("[on] [new] litebans kick:", SimpleEvent.class, KickEvent.class);
		Skript.registerEvent("[on] [new] litebans warn:", SimpleEvent.class, WarnEvent.class);

		Classes.registerClass(new ClassInfo<>(Entry.class, "entry")
				.defaultExpression(new EventValueExpression<>(Entry.class))
				.user("entry").name("entry")
				.parser(new Parser<Entry>() {
							@Override
							public boolean canParse(ParseContext context) {
								return false;
							}

							@Override
							public Entry parse(String string, ParseContext parseContext) {
								return null;
							}

							public String toString(Entry entry, int flags) {
								return Integer.toString(entry.getId());
							}

							public String toVariableNameString(Entry entry) {
								return Integer.toString(entry.getId());
							}

							public String getVariableNamePattern() {
								return ".+";
							}
						}
				));

		getAddonInstance().loadClasses("me.pugabyte.litebanssk", "skript");
	}
}