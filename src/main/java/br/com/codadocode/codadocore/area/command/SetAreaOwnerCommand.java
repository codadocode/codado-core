package br.com.codadocode.codadocore.area.command;

import br.com.codadocode.codadocore.area.AreaData;
import br.com.codadocode.codadocore.area.AreaInfo;
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

            if (strings.length != 1) return false;

            String playerName = strings[0];
            AreaManager areaManager = AreaManager.getInstance();

            AreaInfo areaInfo = areaManager.checkVector3InsideArea(playerPosition);
            Optional<AreaData> optFinalArea = areaInfo.getSubArea().isPresent() ? areaInfo.getSubArea() : areaInfo.getMainArea();

            if (optFinalArea.isEmpty()){
                player.sendMessage("Você não está dentro de nenhuma area!");
                return true;
            }

            AreaData areaData = optFinalArea.get();
            if (!areaData.isOwner(player) || !player.hasPermission("br.com.codadocode.codadocore.area.admin")) {
                player.sendMessage("Você não pode trocar o dono dessa região");
                return true;
            }

            Optional<Player> optPlayer = ServerUtility.getPlayerByName(playerName);
            if (optPlayer.isEmpty()) {
                player.sendMessage("Player '" + playerName + "' não está online!");
                return true;
            }

            Player findedPlayer = optPlayer.get();
            areaData.setOwner(findedPlayer);
            return true;
        }

        return false;
    }
}
