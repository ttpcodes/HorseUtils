package io.tehtotalpwnage.horseutils.configs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import org.slf4j.Logger;

import io.tehtotalpwnage.horseutils.HorseUtils;
import io.tehtotalpwnage.horseutils.utils.TranslationHelper;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class Config {
	
	private static Config instance = new Config();
	public static Config getInstance() {
		return instance;
	}
	
	public CommentedConfigurationNode getNode() {
		return node;
	}
	
	private Logger logger = HorseUtils.getInstance().getLogger();
	
	private String file = "config.conf";
	private Path path = Paths.get(HorseUtils.getInstance().getConfigPath() + "/" + file);
	private ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
		.setPath(path).build();
	private CommentedConfigurationNode node;
	
	public void load() {
		if (!Files.exists(path)) {
			node = ConfigHelper.getInstance().load(file, path, loader);
			populate();
		} else {
			node = ConfigHelper.getInstance().load(file, path, loader);
		}
	}
	
	public Locale loadLocale() {
		try {
			Locale locale = new Locale(loader.load().getNode("locale").getValue().toString());
			logger.info("Loaded locale: '" + locale + "' for plugin HorseUtils");
			return locale;
		} catch (Exception e) {
			logger.error("Could not load locale (This is normal for first run.)");
			logger.info("Loaded default locale: '" + Locale.getDefault() + "' for plugin HorseUtils");
			return Locale.getDefault();
		}	
	}
	
	public void populate() {
		node.getNode("locale").setValue("en")
			.setComment("The two letter language code for a locale. Default is 'en'.");
		node.getNode("disable_environment_damage").setValue(false)
			.setComment("Whether to disable environmental damage for horses.");
		node.getNode("disable_mob_damage").setValue(false)
			.setComment("Whether to prevent mobs from dealing damage to horses.");
		node.getNode("disable_owner_damage").setValue(false)
			.setComment("Whether to prevent the owner of the horse from dealing damage to it.");
		node.getNode("disable_player_damage").setValue(false)
			.setComment("Whether to disable all damage from players who are not the owner of the horse.");
		logger.info(TranslationHelper.s("string.console.populate", HorseUtils.locale, file));
	}
	
	public void save() {
		ConfigHelper.getInstance().save(file, loader, node);
	}
}