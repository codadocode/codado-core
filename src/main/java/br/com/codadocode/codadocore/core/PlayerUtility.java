package br.com.codadocode.codadocore.core;
import br.com.codadocode.codadocore.tools.BlockSelection;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PlayerUtility {
    public static Optional<List<Vector3>> getPlayerSelectedBlocks(Player player)   {
        PersistentDataContainer persistentContainer = player.getPersistentDataContainer();

        if (!persistentContainer.has(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.xposLeftClickKey), PersistentDataType.DOUBLE)) return Optional.empty();

        double leftBlockPosX = persistentContainer.get(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.xposLeftClickKey), PersistentDataType.DOUBLE);
        double leftBlockPosY = persistentContainer.get(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.yposLeftClickKey), PersistentDataType.DOUBLE);
        double leftBlockPosZ = persistentContainer.get(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.zposLeftClickKey), PersistentDataType.DOUBLE);

        double rightBlockPosX = persistentContainer.get(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.xposRightClickKey), PersistentDataType.DOUBLE);
        double rightBlockPosY = persistentContainer.get(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.yposRightClickKey), PersistentDataType.DOUBLE);
        double rightBlockPosZ = persistentContainer.get(new NamespacedKey(BlockSelection.blockSelectionNamespace, BlockSelection.zposRightClickKey), PersistentDataType.DOUBLE);

        Vector3 leftBlock = new Vector3(leftBlockPosX, leftBlockPosY, leftBlockPosZ);
        Vector3 rightBlock = new Vector3(rightBlockPosX, rightBlockPosY, rightBlockPosZ);

        return Optional.of(List.of(leftBlock, rightBlock));
    }
}
