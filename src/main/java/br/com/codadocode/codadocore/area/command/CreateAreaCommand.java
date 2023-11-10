package br.com.codadocode.codadocore.area.command;

import br.com.codadocode.codadocore.area.AreaData;
import br.com.codadocode.codadocore.area.AreaInfo;
import br.com.codadocode.codadocore.area.AreaManager;
import br.com.codadocode.codadocore.area.AreaSize;
import br.com.codadocode.codadocore.core.ConvertUtility;
import br.com.codadocode.codadocore.core.PlayerUtility;
import br.com.codadocode.codadocore.core.Vector3;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CreateAreaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player)commandSender;
            Vector3 playerPosition = ConvertUtility.locationToVector3(player.getLocation());

            if (strings.length != 1) return false;

            String areaName = strings[0];
            Optional<List<Vector3>> optPlayerSelectedBlocks = PlayerUtility.getPlayerSelectedBlocks(player);
            if (optPlayerSelectedBlocks.isEmpty())   {
                //MESSAGE
                return true;
            }

            AreaManager areaManager = AreaManager.getInstance();
            List<Vector3> playerSelectedBlocks = optPlayerSelectedBlocks.get();

            AreaInfo firstAreaInfo = areaManager.checkVector3InsideArea(playerSelectedBlocks.get(0));
            AreaInfo secondAreaInfo = areaManager.checkVector3InsideArea(playerSelectedBlocks.get(0));

            Optional<AreaData> optFirstArea = firstAreaInfo.getMainArea();
            Optional<AreaData> optSecondArea = secondAreaInfo.getMainArea();

            AreaSize areaSize = new AreaSize(playerSelectedBlocks.get(0), playerSelectedBlocks.get(1));

            if ((optFirstArea.isPresent() && optSecondArea.isEmpty()) || (optFirstArea.isEmpty() && optSecondArea.isPresent()))   {
                player.sendMessage("Não é possível criar uma SubArea dentro e fora de uma região!");
                return true;
            }

            if (optFirstArea.isEmpty() && optSecondArea.isEmpty())   {
                try {
                    AreaData newMainArea = new AreaData(areaName, areaSize, player, false);
                    areaManager.createArea(player, newMainArea);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }

            if (optFirstArea.isPresent() && optSecondArea.isPresent())   {
                AreaData firstAreaData = optFirstArea.get();
                AreaData secondAreaData = optSecondArea.get();

                if (!firstAreaData.equals(secondAreaData)) {
                    player.sendMessage("Não é possível criar uma SubArea dentro de regiões diferentes! A seleção de posição deve estar dentro da mesma região!");
                    return true;
                }

                try {
                    AreaData newSubArea = new AreaData(areaName, areaSize, player, false);
                    firstAreaData.createSubArea(player, newSubArea);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        }

        return false;
    }
}
