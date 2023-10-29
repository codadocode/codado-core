package br.com.codadocode.codadocore.waypoint.event;

import br.com.codadocode.codadocore.CodadoCore;
import br.com.codadocode.codadocore.waypoint.WaypointInfo;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class WaypointEvent implements Listener {

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event)   {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block == null) return;
        if (!block.getType().equals(Material.BEACON)) return;

        TileState blockState = (TileState) block.getState();
        PersistentDataContainer dataContainer = blockState.getPersistentDataContainer();

        if (dataContainer.has(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.waypointActiveKey), PersistentDataType.BOOLEAN))   {
            double posX = dataContainer.get(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.xKey), PersistentDataType.DOUBLE);
            double posY = dataContainer.get(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.yKey), PersistentDataType.DOUBLE);
            double posZ = dataContainer.get(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.zKey), PersistentDataType.DOUBLE);
            String worldName = dataContainer.get(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.worldKey), PersistentDataType.STRING);
            String waypointName = dataContainer.get(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.waypointNameKey), PersistentDataType.STRING);

            World findedWorld = Bukkit.getWorld(worldName);
            if (findedWorld == null) return;

            Location tpLocation = new Location(findedWorld, posX, posY, posZ);
            player.teleport(tpLocation);
            player.sendMessage("Você foi teleportado para o waypoint '" + waypointName + "'.");

            event.setCancelled(true);
        }
    }
}
