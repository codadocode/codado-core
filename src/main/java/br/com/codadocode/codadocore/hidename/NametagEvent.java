package br.com.codadocode.codadocore.hidename;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NametagEvent implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)   {
        Player eventPlayer = event.getPlayer();
        NametagManager.instance.hidePlayerName(eventPlayer);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)   {
        Player eventPlayer = event.getPlayer();
        NametagManager.instance.unhidePlayerName(eventPlayer);
    }
}
