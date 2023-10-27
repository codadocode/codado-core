package br.com.codadocode.codadocore.area;

import br.com.codadocode.codadocore.core.CodadoLog;
import br.com.codadocode.codadocore.core.DataFolder;
import br.com.codadocode.codadocore.core.JsonManager;
import br.com.codadocode.codadocore.core.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class AreaManager {
    private static AreaManager instance;

    private DataFolder dataFolder;
    private JsonManager jsonManager;
    private CodadoLog log;
    private Map<String, AreaData> areas;
    private Map<Player, AreaPlayer> players;

    public AreaManager(File dataFolder, String subDataFolder)   {
        if (instance == null) instance = this;

        this.log = new CodadoLog("AreaManager");
        this.areas = new HashMap<String, AreaData>();
        this.players = new HashMap<>();
        this.dataFolder = new DataFolder(dataFolder, subDataFolder);
        this.jsonManager = new JsonManager(this.dataFolder);
    }

    public static AreaManager getInstance()   {
        return instance;
    }

    public boolean registerPlayerOnManager(Player player)   {
        if (this.players.containsKey(player)) return false;

        AreaPlayer areaPlayer = new AreaPlayer(player);
        this.players.put(player, areaPlayer);
        return true;
    }

    public boolean unRegisterPlayerOnManager(Player player)   {
        if (!this.players.containsKey(player)) return false;

        this.players.remove(player);
        return true;
    }

    public void updatePlayerRegion(Player player)   {
        Optional<AreaPlayer> optAreaPlayer = getAreaPlayer(player);
        if (optAreaPlayer.isEmpty()) return;

        AreaPlayer areaPlayer = optAreaPlayer.get();
        for (AreaData areaData : this.areas.values())   {
            Optional<AreaData> optInsideArea = areaData.checkPlayerInside(areaPlayer.getPlayer());
            if (optInsideArea.isEmpty()) continue;

            AreaData insideArea = optInsideArea.get();

            areaPlayer.setActualRegion(insideArea);
            return;
        }

        areaPlayer.setActualRegion(null);
    }

    public Optional<AreaPlayer> getAreaPlayer(Player player)   {
        if (!this.players.containsKey(player)) return Optional.empty();

        return Optional.of(this.players.get(player));
    }

    public boolean createArea(Player sender, AreaData areaData) throws IOException {
        if (this.areas.containsKey(areaData.getAreaName())) return false;

        this.areas.put(areaData.getAreaName(), areaData);
        this.jsonManager.saveToFile(areaData.getAreaName(), areaData);
        this.log.showInfo("Area '" + areaData.getAreaName() + "' foi criada com sucesso!");
        return true;
    }

    public boolean removeArea(Player sender, String areaName)   {
        if (!this.areas.containsKey(areaName)) return false;

        AreaData areaData = this.areas.get(areaName);
        if (!areaData.isOwner(sender) || sender.hasPermission("br.com.codadocode.codadocore.area.admin")) return false;

        this.areas.remove(areaName);
        this.log.showInfo("Area '" + areaData.getAreaName() + "' foi removida com sucesso!");
        return true;
    }

    public Optional<AreaData> getAreaByName(String areaName)   {
        if (!this.areas.containsKey(areaName)) return Optional.empty();

        return Optional.of(this.areas.get(areaName));
    }

    public Optional<AreaData> checkVector3InsideArea(Vector3 position)   {
        for (AreaData areaData : this.areas.values())   {
            if (!areaData.getAreaSize().isInside(position)) continue;

            return Optional.of(areaData);
        }

        return Optional.empty();
    }

    public void loadAllAreaData() throws FileNotFoundException {
        Optional<List<Object>> optionalAreaDataList = this.jsonManager.loadAllFiles(AreaData.class);
        if (optionalAreaDataList.isPresent())   {
            List<Object> areaDataList = optionalAreaDataList.get();
            for (int i = 0; i < areaDataList.size(); i++)   {
                AreaData actualAreaData = (AreaData)areaDataList.get(i);
                this.areas.put(actualAreaData.getAreaName(), actualAreaData);
            }
        }
        if (this.areas.size() > 0)   {
            Bukkit.getServer().getLogger().info("All area data has been loaded!");
        }else   {
            Bukkit.getServer().getLogger().info("Area data empty!");
        }
    }

    public JsonManager getJsonManager()   {
        return this.jsonManager;
    }
}
