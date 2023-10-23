package br.com.codadocode.codadocore.area.command;

import br.com.codadocode.codadocore.area.AreaData;
import br.com.codadocode.codadocore.area.AreaManager;
import br.com.codadocode.codadocore.area.AreaSize;
import br.com.codadocode.codadocore.core.ConvertUtility;
import br.com.codadocode.codadocore.core.ServerUtility;
import br.com.codadocode.codadocore.core.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SetAreaOwnerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player)commandSender;
            Vector3 playerPosition = ConvertUtility.locationToVector3(player.getLocation());

            if (strings.length != 2) return false;

            String areaName = strings[0];
            String playerName = strings[1];

            AreaManager areaManager = (AreaManager)AreaManager.getInstance();
            Optional<AreaData> optAreaData = areaManager.getAreaByName(areaName);
            if (optAreaData.isEmpty()){
                //MESSAGE
                return true;
            }

            AreaData areaData = optAreaData.get();
            if (!areaData.isOwner(player) || !player.hasPermission("br.com.codadocode.codadocore.area.admin")) {
                //MESSAGE
                return true;
            }

            Optional<Player> optPlayer = ServerUtility.getPlayerByName(playerName);
            if (optPlayer.isEmpty()) {
                //MESSAGE
                return true;
            }

            Player findedPlayer = optPlayer.get();
            areaData.setOwner(findedPlayer);
            return true;
        }

        return false;
    }
}
