package io.tehtotalpwnage.horseutils.configs;

import java.nio.file.Path;
import java.nio.file.Paths;

import io.tehtotalpwnage.horseutils.HorseUtils;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class HorseList {
	
	private static HorseList instance = new HorseList();
	public static HorseList getInstance() {
		return instance;
	}
	
	public CommentedConfigurationNode getNode() {
		return node;
	}
	
	private String file = "horselist.conf";
	private Path path = Paths.get(HorseUtils.getInstance().getConfigPath() + "/" + file);
	private ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
		.setPath(path).build();
	private CommentedConfigurationNode node;
	
	public void load() {
		node = ConfigHelper.getInstance().load(file, path, loader);
	}
	
	public void save() {
		ConfigHelper.getInstance().save(file, loader, node);
	}
}
