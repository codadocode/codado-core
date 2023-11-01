package br.com.codadocode.codadocore.tools.event;

import br.com.codadocode.codadocore.core.ConvertUtility;
import br.com.codadocode.codadocore.core.Vector3;
import br.com.codadocode.codadocore.tools.BlockSelection;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BlockSelectEvent implements Listener {

    @EventHandler
    public void onPlayerInteraction(PlayerInteractEvent event)   {
        if (!event.getAction().equals(Action.LEFT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;

        Player player = event.getPlayer();
        Action playerAction = event.getAction();

        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        Block selectedBlock = event.getClickedBlock();;
        Vector3 blockPosition = ConvertUtility.locationToVector3(selectedBlock.getLocation());

        switch (playerAction)   {
            case LEFT_CLICK_BLOCK:
                dataContainer.set(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.xposLeftClickKey), PersistentDataType.DOUBLE, blockPosition.getX());
                dataContainer.set(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.yposLeftClickKey), PersistentDataType.DOUBLE, blockPosition.getY());
                dataContainer.set(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.zposLeftClickKey), PersistentDataType.DOUBLE, blockPosition.getZ());

                Bukkit.getLogger().info("Left Block Selected!!!");
                break;
            case RIGHT_CLICK_BLOCK:
                dataContainer.set(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.xposRightClickKey), PersistentDataType.DOUBLE, blockPosition.getX());
                dataContainer.set(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.yposRightClickKey), PersistentDataType.DOUBLE, blockPosition.getY());
                dataContainer.set(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.zposRightClickKey), PersistentDataType.DOUBLE, blockPosition.getZ());

                Bukkit.getLogger().info("Right Block Selected!!!");
                break;
        }
    }
}
