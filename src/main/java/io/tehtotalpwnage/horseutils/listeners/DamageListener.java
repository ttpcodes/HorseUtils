package io.tehtotalpwnage.horseutils.listeners;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.First;

import io.tehtotalpwnage.horseutils.configs.Config;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
public class DamageListener {
	CommentedConfigurationNode node = Config.getInstance().getNode();
	@Listener
	public void onDamageEntity(DamageEntityEvent event, @First EntityDamageSource source) {
		Entity target = event.getTargetEntity();
		if (target == EntityTypes.HORSE) {
			if (source.getSource() == EntityTypes.PLAYER) {
				Player player = (Player) source.getSource();
				if (player.getUniqueId() == Config.getInstance().getNode().getNode("horses", target.getUniqueId(), "owner")
					.getValue() && Config.getInstance().getNode().getNode("disable_owner_damage").getBoolean()) {
						event.setCancelled(true);
				} else if (Config.getInstance().getNode().getNode("disable_player_damage").getBoolean()) {
						event.setCancelled(true);
				}
			} else if (source.getType() == DamageTypes.ATTACK && source.getSource() != EntityTypes.PLAYER &&
				Config.getInstance().getNode().getNode("disable_mob_damage").getBoolean()) {
					event.setCancelled(true);
			} else if (source.getType() != DamageTypes.ATTACK && node.getNode("disable_environment_damage").getBoolean()){
				event.setCancelled(true);
			}
		}
	}
}
