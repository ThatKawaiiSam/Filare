package io.github.thatkawaiisam.filare;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class BukkitConfiguration extends BaseConfiguration {

	private JavaPlugin plugin;
	private YamlConfiguration base;

	/**
	 * Bukkit Configuration.
	 *
	 * @param plugin    Plugin instance.
	 * @param name      Name of file.
	 * @param directory Directory of file.
	 */
	public BukkitConfiguration(JavaPlugin plugin, String name, String directory) {
		super(name, directory);
		this.plugin = plugin;
		this.base = new YamlConfiguration();
	}

	@Override
	void onCreate() {
		// Don't need to check if the file exists as the false parameter
		// already ensures we do not replace any existing file
		plugin.saveResource(getName() + ".yml", false);
	}

	@Override
	public void load() {
		try {
			base.load(this.getFile());
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		try {
			base.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public YamlConfiguration getImplementation() {
		return base;
	}

}
