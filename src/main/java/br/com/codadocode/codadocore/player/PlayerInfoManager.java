package br.com.codadocode.codadocore.player;

import br.com.codadocode.codadocore.core.BaseSingleton;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfoManager extends BaseSingleton {
    private List<PlayerInfo> players;

    public PlayerInfoManager()   {
        this.players = new ArrayList<>();
    }

    public boolean addPlayer(Player player)   {
        return false;
    }

    public boolean removePlayer(Player player)   {
        return false;
    }
}
