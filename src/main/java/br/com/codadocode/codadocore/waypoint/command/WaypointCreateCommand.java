package br.com.codadocode.codadocore.waypoint.command;

import br.com.codadocode.codadocore.waypoint.WaypointInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class WaypointCreateCommand implements CommandExecutor {

    private Set<Material> waypointBlockSet;
    private int distanceToFind;

    public WaypointCreateCommand()   {
        this.waypointBlockSet = new HashSet<>();
        this.waypointBlockSet.add(Material.BEACON);
        this.distanceToFind = 4;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player)commandSender;

            if (strings.length != 5) return false;

            String worldName = strings[0];
            double xPos = Double.parseDouble(strings[1]);
            double yPos = Double.parseDouble(strings[2]);
            double zPos = Double.parseDouble(strings[3]);
            String waypointName = strings[4];
            boolean waypointActive = true;

            Block waypointBlock = player.getTargetBlock(null, 200);
            if  (waypointBlock == null || !waypointBlock.getType().equals(Material.BEACON))   {
                player.sendMessage("Não foi possível encontrar um bloco para criar um Waypoint");
                return true;
            }

            TileState blockState = (TileState) waypointBlock.getState();
            PersistentDataContainer dataContainer = blockState.getPersistentDataContainer();
            dataContainer.set(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.waypointActiveKey), PersistentDataType.BOOLEAN, waypointActive);
            dataContainer.set(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.xKey), PersistentDataType.DOUBLE, xPos);
            dataContainer.set(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.yKey), PersistentDataType.DOUBLE, yPos);
            dataContainer.set(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.zKey), PersistentDataType.DOUBLE, zPos);
            dataContainer.set(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.worldKey), PersistentDataType.STRING, worldName);
            dataContainer.set(new NamespacedKey(WaypointInfo.waypointNamespace, WaypointInfo.waypointNameKey), PersistentDataType.STRING, waypointName);
            blockState.update();

            player.sendMessage("Waypoint '" + waypointName + "' foi criado com sucesso!");
            return true;
        }

        return false;
    }
}
