package br.com.codadocode.codadocore.area.event;

import br.com.codadocode.codadocore.area.AreaManager;
import br.com.codadocode.codadocore.hidename.NametagManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AreaEvent implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)   {
        Player eventPlayer = event.getPlayer();
        AreaManager areaManager = AreaManager.getInstance().cast();
        boolean success = areaManager.registerPlayerOnManager(eventPlayer);
        if (success) Bukkit.getLogger().info("Jogador '" + eventPlayer.getName() + "' foi registrado com sucesso no AreaManager!");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)   {
        Player eventPlayer = event.getPlayer();
        NametagManager.instance.unhidePlayerName(eventPlayer);
        AreaManager areaManager = AreaManager.getInstance().cast();
        boolean success = areaManager.unRegisterPlayerOnManager(eventPlayer);
        if (success) Bukkit.getLogger().info("Jogador '" + eventPlayer.getName() + "' foi removido com sucesso do AreaManager!");
    }
}
