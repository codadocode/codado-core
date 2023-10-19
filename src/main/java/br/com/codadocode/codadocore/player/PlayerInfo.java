package br.com.codadocode.codadocore.player;

import br.com.codadocode.codadocore.area.AreaData;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class PlayerInfo {
    private Player player;
    private AreaData actualRegion;

    public PlayerInfo(Player player)   {
        this.player = player;
    }

    public UUID getPlayerID()   {
        return this.player.getUniqueId();
    }

    public Optional<AreaData> getActualRegion()   {
        if (this.actualRegion == null) return Optional.empty();

        return Optional.of(this.actualRegion);
    }

    public boolean isInsideRegion()   {
        if (this.actualRegion == null) return false;

        return true;
    }
}
