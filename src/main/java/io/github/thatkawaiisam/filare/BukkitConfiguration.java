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
     * @param plugin instance.
     * @param name of file.
     * @param directory of file.
     */
    public BukkitConfiguration(JavaPlugin plugin, String name, String directory) {
        super(name, directory);
        this.plugin = plugin;
        this.base = new YamlConfiguration();

        // Trigger on create.
        this.onCreate();
    }

    @Override
    void onCreate() {
        if (!getFile().exists()) {
            this.plugin.saveResource(this.getName() + ".yml", false);
        }
    }

    @Override
    public void load() {
        try {
            this.base.load(this.getFile());
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        try {
            this.base.save(this.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getImplementation() {
        return this.base;
    }

}
