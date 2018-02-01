package me.pugabyte.litebanssk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.Timespan;
import ch.njol.skript.variables.Variables;
import litebans.api.Entry;
import litebans.api.Events;
import me.pugabyte.litebanssk.skript.events.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.IOException;

public class LiteBansSk extends JavaPlugin {
	private static LiteBansSk instance;
	private static SkriptAddon addonInstance;
	private Events.Listener entryAdded;
	private Events.Listener broadcastSent;

	public LiteBansSk() {
		if (instance == null) {
			instance = this;
		} else {
			throw new IllegalStateException();
		}
	}

	public static LiteBansSk getInstance() {
		if (instance == null) {
			throw new IllegalStateException();
		}
		return instance;
	}

	public static SkriptAddon getAddonInstance() {
		if (addonInstance == null) {
			addonInstance = Skript.registerAddon(getInstance());
		}
		return addonInstance;
	}

	@Override
	public void onEnable() {
		String version = getInstance().getServer().getPluginManager().getPlugin("LiteBans").getDescription().getVersion();

		if (version.startsWith("2.")) {
			double versionDouble;
			try {
				versionDouble = Double.parseDouble(version.replaceFirst("2.", ""));

				// Most recent version I've built against
				if (versionDouble < 2.5) {
					getLogger().warning("LiteBans is not updated; some features may not work!");
					getLogger().warning("Update: https://www.spigotmc.org/resources/3715/");
				}
			} catch (NumberFormatException e) {
				getLogger().warning("Could not parse LiteBans version '" + version + "'");
			}
			Player player = Bukkit.getPlayer("Pugabyte");
			Timespan hours = (Timespan) Variables.getVariable("hours::" + player.getUniqueId(), null, false);
			long millis = Long.parseLong(hours.toString());
			if (millis >= 3600000) {

			}

		}
		try {
			register();
		} catch (Exception e) {
			getLogger().severe("Report this error to https://github.com/Pugabyte/LiteBansSk/issues");
			getLogger().severe("Please include LiteBans, LiteBansSk, and Spigot versions");
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		Events.get().unregister(entryAdded);
		Events.get().unregister(broadcastSent);
	}

	public void register() throws Exception {
		entryAdded = new Events.Listener() {
			@Override
			public void entryAdded(Entry entry) {
				if (entry.getType().equals("ban")) {
					instance.getServer().getScheduler().runTaskLater(instance, () ->
						instance.getServer().getPluginManager().callEvent(new BanEvent(entry))
					, 0);
				} else if (entry.getType().equals("mute")) {
					instance.getServer().getScheduler().runTaskLater(instance, () ->
						instance.getServer().getPluginManager().callEvent(new MuteEvent(entry))
					, 0);
				} else if (entry.getType().equals("kick")) {
					instance.getServer().getScheduler().runTaskLater(instance, () ->
						instance.getServer().getPluginManager().callEvent(new KickEvent(entry))
					, 0);
				} else if (entry.getType().equals("warn")) {
					instance.getServer().getScheduler().runTaskLater(instance, () ->
						instance.getServer().getPluginManager().callEvent(new WarnEvent(entry))
					, 0);
				}
			}
		};

		broadcastSent = new Events.Listener() {
			@Override
			public void broadcastSent(String message, String type) {
				getInstance().getServer().getPluginManager().callEvent(new BroadcastEvent(message, type));
			}
		};

		Events.get().register(entryAdded);
		Events.get().register(broadcastSent);

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

		getAddonInstance().loadClasses("me.pugabyte.litebanssk.skript");
	}
}
