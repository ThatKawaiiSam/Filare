package io.github.thatkawaiisam.filare;

import lombok.Getter;

import java.io.File;

@Getter
public abstract class BaseConfiguration {

    private File file;
    private String name, directory;

    /**
     * Base Configuration.
     *
     * @param name of file.
     * @param directory of file.
     */
    public BaseConfiguration(String name, String directory) {
        this.name = name;
        this.directory = directory;

        // Setup File.
        this.file = new File(this.directory, this.name + ".yml");
    }

    /**
     * Trigger on file create.
     */
    abstract void onCreate();

    /**
     * Load data from file.
     */
    abstract public void load();

    /**
     * Commit changes to file.
     */
    abstract public void save();

}
