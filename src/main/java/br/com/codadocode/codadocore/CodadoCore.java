package br.com.codadocode.codadocore;

import br.com.codadocode.codadocore.area.event.AreaEvent;
import br.com.codadocode.codadocore.core.BaseSingleton;
import br.com.codadocode.codadocore.core.DataFolder;
import br.com.codadocode.codadocore.core.Vector3;
import br.com.codadocode.codadocore.hidename.NametagEvent;
import br.com.codadocode.codadocore.hidename.NametagManager;
import br.com.codadocode.codadocore.worldspawn.WorldSpawnData;
import br.com.codadocode.codadocore.worldspawn.WorldSpawnManager;
import br.com.codadocode.codadocore.worldspawn.command.SetSpawnCommand;
import br.com.codadocode.codadocore.worldspawn.command.SpawnCommand;
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
        registerCommands();
        loadData();
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

    private void registerCommands()   {
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand());
    }

    private void loadData()   {
        this.worldSpawnManager.loadAllSpawnData();
    }

    private void buildManagers()   {
        this.nametagManager = new NametagManager();
        this.worldSpawnManager = new WorldSpawnManager(this.dataFolder, "worldspawns");
    }

    private void registerEvents()   {
        this.getServer().getPluginManager().registerEvents(new NametagEvent(), this);
        this.getServer().getPluginManager().registerEvents(new AreaEvent(), this);
    }
}
