package br.com.codadocode.codadocore.area.event;
import br.com.codadocode.codadocore.area.AREA_FLAG;
import br.com.codadocode.codadocore.area.AreaData;
import br.com.codadocode.codadocore.area.AreaManager;
import br.com.codadocode.codadocore.area.AreaPlayer;
import br.com.codadocode.codadocore.core.ConvertUtility;
import br.com.codadocode.codadocore.core.Vector3;
import br.com.codadocode.codadocore.hidename.NametagManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Optional;

public class AreaEvent implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)   {
        Player eventPlayer = event.getPlayer();
        AreaManager areaManager = (AreaManager)AreaManager.getInstance();
        boolean success = areaManager.registerPlayerOnManager(eventPlayer);
        if (success) Bukkit.getLogger().info("Jogador '" + eventPlayer.getName() + "' foi registrado com sucesso no AreaManager!");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)   {
        Player eventPlayer = event.getPlayer();
        NametagManager.instance.unhidePlayerName(eventPlayer);
        AreaManager areaManager = (AreaManager)AreaManager.getInstance();
        boolean success = areaManager.unRegisterPlayerOnManager(eventPlayer);
        if (success) Bukkit.getLogger().info("Jogador '" + eventPlayer.getName() + "' foi removido com sucesso do AreaManager!");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)   {
        Player player = event.getPlayer();
        Vector3 blockPosition = ConvertUtility.locationToVector3(event.getBlock().getLocation());
        AreaManager manager = AreaManager.getInstance();
        Optional<AreaData> optInteractionAreaData = manager.checkVector3InsideArea(blockPosition);

        if (optInteractionAreaData.isEmpty()) return;
        AreaData interactionAreaData = optInteractionAreaData.get();

        if (interactionAreaData.isOwner(player) || interactionAreaData.isMember(player) || player.hasPermission("br.com.codadocode.codadocore.area.admin")) return;

        boolean flagValue = interactionAreaData.getFlagValue(AREA_FLAG.BREAK);
        event.setCancelled(!flagValue);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)   {
        Player player = event.getPlayer();
        Vector3 blockPosition = ConvertUtility.locationToVector3(event.getBlock().getLocation());
        AreaManager manager = AreaManager.getInstance();
        Optional<AreaData> optInteractionAreaData = manager.checkVector3InsideArea(blockPosition);

        if (optInteractionAreaData.isEmpty()) return;
        AreaData interactionAreaData = optInteractionAreaData.get();

        if (interactionAreaData.isOwner(player) || interactionAreaData.isMember(player) || player.hasPermission("br.com.codadocode.codadocore.area.admin")) return;

        boolean flagValue = interactionAreaData.getFlagValue(AREA_FLAG.BREAK);
        event.setCancelled(!flagValue);
    }

    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent event)   {
        if (!event.hasBlock()) return;

        Player player = event.getPlayer();
        Vector3 blockPosition = ConvertUtility.locationToVector3(event.getClickedBlock().getLocation());
        AreaManager manager = AreaManager.getInstance();
        Optional<AreaData> optInteractionAreaData = manager.checkVector3InsideArea(blockPosition);

        if (optInteractionAreaData.isEmpty()) return;
        AreaData interactionAreaData = optInteractionAreaData.get();

        if (interactionAreaData.isOwner(player) || interactionAreaData.isMember(player) || player.hasPermission("br.com.codadocode.codadocore.area.admin")) return;

        boolean flagValue = interactionAreaData.getFlagValue(AREA_FLAG.INTERACT);
        event.setCancelled(!flagValue);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event)   {
        if (event.getDamager() instanceof  Player || event.getEntity() instanceof Player)   {
            Player damagerPlayer = (Player)event.getDamager();
            Player victimPlayer = (Player)event.getEntity();

            Vector3 damagerPosition = ConvertUtility.locationToVector3(damagerPlayer.getLocation());
            Vector3 victimPosition = ConvertUtility.locationToVector3(victimPlayer.getLocation());

            AreaManager manager = AreaManager.getInstance();

            Optional<AreaData> optDamagerAreaData = manager.checkVector3InsideArea(damagerPosition);
            if (optDamagerAreaData.isPresent())   {
                AreaData damagerAreaData = optDamagerAreaData.get();

                if (damagerPlayer.hasPermission("br.com.codadocode.codadocore.area.admin")) return;

                boolean damagerFlagValue = damagerAreaData.getFlagValue(AREA_FLAG.PVP);
                event.setCancelled(!damagerFlagValue);

                return;
            }

            Optional<AreaData> optVictimAreaData = manager.checkVector3InsideArea(victimPosition);
            if (optVictimAreaData.isPresent())   {
                AreaData victimAreaData = optDamagerAreaData.get();

                boolean victimFlagValue = victimAreaData.getFlagValue(AREA_FLAG.PVP);
                event.setCancelled(!victimFlagValue);

                return;
            }
        }
    }

    @EventHandler
    public void onEntityExplosion(EntityExplodeEvent event)   {
        Entity entity = event.getEntity();
        Vector3 entityPosition = ConvertUtility.locationToVector3(entity.getLocation());
        AreaManager manager = AreaManager.getInstance();

        Optional<AreaData> optAreaData = manager.checkVector3InsideArea(entityPosition);
        if (optAreaData.isEmpty()) return;

        AreaData areaData = optAreaData.get();
        boolean flagValue = areaData.getFlagValue(AREA_FLAG.MONSTER_EXPLOSION);

        if (!flagValue) event.setCancelled(true);
    }
}
