package br.com.codadocode.codadocore.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServerUtility {
    public static Optional<Player> getPlayerByName(String playerName)   {
        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (int i = 0; i < onlinePlayers.size(); i++)   {
            Player actualPlayer = onlinePlayers.get(i);

            if (actualPlayer.getName().equals(playerName)) return Optional.of(actualPlayer);
        }

        return Optional.empty();
    }
}
