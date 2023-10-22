package br.com.codadocode.codadocore.area;

import br.com.codadocode.codadocore.core.BaseSingleton;
import br.com.codadocode.codadocore.core.CodadoLog;
import org.bukkit.entity.Player;
import java.util.*;

public class AreaManager extends BaseSingleton {
    private CodadoLog log;
    private Map<String, AreaData> areas;
    private Map<Player, AreaPlayer> players;

    public AreaManager()   {
        super();
        this.log = new CodadoLog("AreaManager");
        this.areas = new HashMap<String, AreaData>();
        this.players = new HashMap<>();
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
            this.log.showInfo(areaPlayer.getPlayer().getName() + " esta dentro da area '" + insideArea.getAreaName() + "'");
            return;
        }

        areaPlayer.setActualRegion(null);
    }

    private Optional<AreaPlayer> getAreaPlayer(Player player)   {
        if (!this.players.containsKey(player)) return Optional.empty();

        return Optional.of(this.players.get(player));
    }

    public boolean createArea(Player sender, AreaData areaData)   {
        if (this.areas.containsKey(areaData.getAreaName())) return false;

        this.areas.put(areaData.getAreaName(), areaData);
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
}
