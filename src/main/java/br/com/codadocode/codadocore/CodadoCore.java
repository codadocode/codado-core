package br.com.codadocode.codadocore;

import br.com.codadocode.codadocore.core.DataFolder;
import br.com.codadocode.codadocore.core.Vector3;
import br.com.codadocode.codadocore.hidename.NametagEvent;
import br.com.codadocode.codadocore.hidename.NametagManager;
import br.com.codadocode.codadocore.worldspawn.WorldSpawnData;
import br.com.codadocode.codadocore.worldspawn.WorldSpawnManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class CodadoCore extends JavaPlugin {
    private File dataFolder;
    private NametagManager nametagManager;
    private WorldSpawnManager worldSpawnManager;

    @Override
    public void onEnable()   {
        this.getLogger().info("Initializing codado core features...");
        initialize();
        buildManagers();
        registerEvents();
        this.getLogger().info("Finished!");
    }

    @Override
    public void onDisable()   {
        this.getLogger().info("Unloding codado core features...");
    }

    private void initialize()   {
        prepareDataFolder();
    }

    private void prepareDataFolder()   {
        if (!this.getDataFolder().exists()) this.getDataFolder().mkdirs();
        this.dataFolder = this.getDataFolder();
        DataFolder dataFolder = new DataFolder(this.dataFolder, "worldspawn");
    }

    private void buildManagers()   {
        this.nametagManager = new NametagManager();
        this.worldSpawnManager = new WorldSpawnManager(this.dataFolder, "worldspawn");
    }

    private void registerEvents()   {
        this.getServer().getPluginManager().registerEvents(new NametagEvent(), this);
    }
}
