package br.com.codadocode.codadocore.area.command;

import br.com.codadocode.codadocore.area.AreaData;
import br.com.codadocode.codadocore.area.AreaManager;
import br.com.codadocode.codadocore.area.AreaSize;
import br.com.codadocode.codadocore.core.ConvertUtility;
import br.com.codadocode.codadocore.core.Vector3;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Optional;

public class CreateAreaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player)commandSender;
            Vector3 playerPosition = ConvertUtility.locationToVector3(player.getLocation());

            if (strings.length != 3) return false;

            String areaName = strings[0];
            int areaHorizontalSize = ConvertUtility.stringToInt(strings[1]);
            int areaVerticalSize = ConvertUtility.stringToInt(strings[2]);
            AreaSize areaSize = new AreaSize(areaHorizontalSize, areaVerticalSize, playerPosition);
            AreaData newAreaData = new AreaData(areaName, areaSize, player);

            AreaManager areaManager = AreaManager.getInstance();
            try {
                areaManager.createArea(player, newAreaData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        return false;
    }
}
