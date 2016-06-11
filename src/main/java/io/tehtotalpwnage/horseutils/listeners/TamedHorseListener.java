package io.tehtotalpwnage.horseutils.listeners;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.TameEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;

import io.tehtotalpwnage.horseutils.configs.Config;

public class TamedHorseListener {
	@Listener
	public void onTameEntity(TameEntityEvent event) {
		Sponge.getServer().getPlayer("TehTotalPwnage").get().sendMessage(Text.of("this fucking works"));
		Entity target = event.getTargetEntity();
/*		if (target.getType() == EntityTypes.HORSE) {
			Sponge.getServer().getPlayer("TehTotalPwnage").get().sendMessage(Text.of("this fucking works"));
			Config.getInstance().getNode().getNode("horses", target.getUniqueId(), "owner").setValue(player.getUniqueId());
			Config.getInstance().save();
		} */
	}
}
