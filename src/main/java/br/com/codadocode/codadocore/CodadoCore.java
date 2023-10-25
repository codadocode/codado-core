package br.com.codadocode.codadocore;

import br.com.codadocode.codadocore.area.AreaManager;
import br.com.codadocode.codadocore.area.command.AddAreaMemberCommand;
import br.com.codadocode.codadocore.area.command.CreateAreaCommand;
import br.com.codadocode.codadocore.area.command.SetAreaOwnerCommand;
import br.com.codadocode.codadocore.area.command.ShowAreaInfoCommand;
import br.com.codadocode.codadocore.area.event.AreaEvent;
import br.com.codadocode.codadocore.core.DataFolder;
import br.com.codadocode.codadocore.hidename.NametagEvent;
import br.com.codadocode.codadocore.hidename.NametagManager;
import br.com.codadocode.codadocore.worldspawn.WorldSpawnManager;
import br.com.codadocode.codadocore.worldspawn.command.SetSpawnCommand;
import br.com.codadocode.codadocore.worldspawn.command.SpawnCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CodadoCore extends JavaPlugin {
    private File dataFolder;
    private NametagManager nametagManager;
    private WorldSpawnManager worldSpawnManager;
    private AreaManager areaManager;

    @Override
    public void onEnable()   {
        this.getLogger().info("Initializing codado core features...");
        initialize();
        buildManagers();
        registerEvents();
        registerCommands();

        try {
            loadData();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        initializeRunnables();
        this.getLogger().info("Finished!");
    }

    @Override
    public void onDisable()   {
        this.getLogger().info("Unloding codado core features...");
    }

    private void initialize()   {
        prepareDataFolder();
    }

    private void initializeRunnables()   {
        new BukkitRunnable()   {

            @Override
            public void run() {
                AreaManager areaManager = (AreaManager) AreaManager.getInstance();
                List<Player> onlinePlayers = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
                for (int i = 0; i < onlinePlayers.size(); i++)   {
                    Player actualPlayer = onlinePlayers.get(i);
                    areaManager.updatePlayerRegion(actualPlayer);
                }
            }
        }.runTaskTimer(this, 0,20L);
    }

    private void prepareDataFolder()   {
        if (!this.getDataFolder().exists()) this.getDataFolder().mkdirs();
        this.dataFolder = this.getDataFolder();
        DataFolder dataFolder = new DataFolder(this.dataFolder, "worldspawn");
    }

    private void registerCommands()   {
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand());

        this.getCommand("areacreate").setExecutor(new CreateAreaCommand());
        this.getCommand("areainfo").setExecutor(new ShowAreaInfoCommand());
        this.getCommand("areasetowner").setExecutor(new SetAreaOwnerCommand());
        this.getCommand("areamemberadd").setExecutor(new AddAreaMemberCommand());
        this.getCommand("areamemberdel").setExecutor(new AddAreaMemberCommand());
    }

    private void loadData() throws FileNotFoundException {
        this.worldSpawnManager.loadAllSpawnData();
        this.areaManager.loadAllAreaData();
    }

    private void buildManagers()   {
        this.nametagManager = new NametagManager();
        this.worldSpawnManager = new WorldSpawnManager(this.dataFolder, "worldspawns");
        this.areaManager = new AreaManager(this.dataFolder, "areas");
    }

    private void registerEvents()   {
        this.getServer().getPluginManager().registerEvents(new NametagEvent(), this);
        this.getServer().getPluginManager().registerEvents(new AreaEvent(), this);
    }
}
