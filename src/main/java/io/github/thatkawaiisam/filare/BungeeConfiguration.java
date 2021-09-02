package io.github.thatkawaiisam.filare;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BungeeConfiguration extends BaseConfiguration {

    private Plugin plugin;
    private Configuration base;

    /**
     * Bungee Configuration.
     *
     * @param plugin instance.
     * @param name of file.
     * @param directory of file.
     */
    public BungeeConfiguration(Plugin plugin, String name, String directory) {
        super(name, directory);
        this.plugin = plugin;
    }

    @Override
    void onCreate() {
    	
    	// Create any parent directories
        plugin.getDataFolder().mkdir();
        getFile().getParentFile().mkdirs();

        // If file does not already exist, then grab it internally from the resources folder
        if (!getFile().exists()) {
            try {
                getFile().createNewFile();
                try (
                        InputStream is = plugin.getResourceAsStream(getName() + ".yml");
                        OutputStream os = new FileOutputStream(getFile())
                ) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to create configuration file", e);
            }

        }
    }

    @Override
    public void load() {
        try {
            base = ConfigurationProvider.getProvider(YamlConfiguration.class).load(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(base, getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getImplementation() {
        return base;
    }
}
