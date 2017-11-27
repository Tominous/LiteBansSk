package me.pugabyte.litebanssk.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import litebans.api.Entry;
import me.pugabyte.litebanssk.LiteBansSk;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class PropPlayerOfEntry extends SimplePropertyExpression<Entry, OfflinePlayer> {

	static {
		PropertyExpression.register(PropPlayerOfEntry.class, OfflinePlayer.class, "player", "entry");
	}

	@Override
	public OfflinePlayer convert(final Entry entry) {
		UUID uuid = UUID.fromString(entry.getUuid());
		return LiteBansSk.getInstance().getServer().getOfflinePlayer(uuid);
	}

	@Override
	public String getPropertyName() {
		return "player";
	}

	@Override
	public Class<? extends OfflinePlayer> getReturnType() {
		return OfflinePlayer.class;
	}
}
