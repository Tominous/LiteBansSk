package me.pugabyte.litebanssk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import litebans.api.Entry;
import litebans.api.Events;
import me.pugabyte.litebanssk.skript.events.*;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.io.IOException;

public class LiteBansSk extends JavaPlugin {
	private static LiteBansSk instance;
	private static SkriptAddon addonInstance;

	public LiteBansSk() {
		if (instance == null) {
			instance = this;
		} else {
			throw new IllegalStateException();
		}
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

	@Override
	public void onEnable() {
		try {
			register();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
	}

	public void register() throws IOException {
		Events.get().register(new Events.Listener() {
			@Override
			public void entryAdded(Entry entry) {
				try {
					if (entry.getType().equals("ban")) {
						getInstance().getServer().getPluginManager().callEvent(new BanEvent(entry));
					} else if (entry.getType().equals("mute")) {
						getInstance().getServer().getPluginManager().callEvent(new MuteEvent(entry));
					} else if (entry.getType().equals("kick")) {
						getInstance().getServer().getPluginManager().callEvent(new KickEvent(entry));
					} else if (entry.getType().equals("warn")) {
						getInstance().getServer().getPluginManager().callEvent(new WarnEvent(entry));
					}
				} catch (Exception e) {
					getLogger().severe("Report this error to https://github.com/Pugabyte/LiteBansSk/issues");
					getLogger().severe("Please include LiteBans, LiteBansSk, and Spigot versions");
					e.printStackTrace();
				}
			}
		});

		Events.get().register(new Events.Listener() {
			@Override
			public void broadcastSent(String message, @Nullable String type) {
				getInstance().getServer().getPluginManager().callEvent(new BroadcastEvent(message, type));
			}
		});

		Skript.registerEvent("litebans entry", SimpleEvent.class, EntryEvent.class, "[on] [new] litebans entry");
		Skript.registerEvent("litebans ban", SimpleEvent.class, BanEvent.class, "[on] [new] litebans ban");
		Skript.registerEvent("litebans mute", SimpleEvent.class, MuteEvent.class, "[on] [new] litebans mute");
		Skript.registerEvent("litebans kick", SimpleEvent.class, KickEvent.class, "[on] [new] litebans kick");
		Skript.registerEvent("litebans warn", SimpleEvent.class, WarnEvent.class, "[on] [new] litebans warn");
		Skript.registerEvent("litebans broadcast", SimpleEvent.class, BroadcastEvent.class, "[on] [new] litebans broadcast");

		EventValues.registerEventValue(BroadcastEvent.class, String.class, new Getter<String, BroadcastEvent>() {
			@Override
			public String get(BroadcastEvent e) {
				return e.getMessage();
			}
		}, 0);

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

		Classes.registerClass(new ClassInfo<>(BroadcastEvent.class, "broadcast")
				.defaultExpression(new EventValueExpression<>(BroadcastEvent.class))
				.user("broadcast").name("broadcast")
				.parser(new Parser<BroadcastEvent>() {
							@Override
							public boolean canParse(ParseContext context) {
								return false;
							}

							@Override
							public BroadcastEvent parse(String string, ParseContext parseContext) {
								return null;
							}

							public String toString(BroadcastEvent event, int flags) {
								return event.getMessage();
							}

							public String toVariableNameString(BroadcastEvent event) {
								return event.getMessage();
							}

							public String getVariableNamePattern() {
								return ".+";
							}
						}
				));

		getAddonInstance().loadClasses("me.pugabyte.litebanssk", "skript");
	}
}
