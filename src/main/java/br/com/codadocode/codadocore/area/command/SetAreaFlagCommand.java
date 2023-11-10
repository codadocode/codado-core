package br.com.codadocode.codadocore.area.command;

import br.com.codadocode.codadocore.area.AreaData;
import br.com.codadocode.codadocore.area.AreaInfo;
import br.com.codadocode.codadocore.area.AreaManager;
import br.com.codadocode.codadocore.core.ConvertUtility;
import br.com.codadocode.codadocore.core.ServerUtility;
import br.com.codadocode.codadocore.core.Vector3;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SetAreaFlagCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player)commandSender;
            Vector3 playerPosition = ConvertUtility.locationToVector3(player.getLocation());

            if (strings.length != 2) return false;

            String flagName = strings[0].toUpperCase();
            String flagValue = strings[1].toLowerCase();

            if (!flagValue.equals("true") && !flagValue.equals("false"))   {
                player.sendMessage("Apenas valores 'true' e 'false' podem ser usados nas flags!");
                return true;
            }

            AreaManager areaManager = AreaManager.getInstance();

            AreaInfo areaInfo = areaManager.checkVector3InsideArea(playerPosition);
            Optional<AreaData> optAreaData = areaInfo.getSubArea().isPresent() ? areaInfo.getSubArea() : areaInfo.getMainArea();
            if (optAreaData.isEmpty()){
                player.sendMessage("Você não está dentro de nenhuma area!");
                return true;
            }

            AreaData areaData = optAreaData.get();
            boolean success = areaData.setFlagValue(flagName, Boolean.valueOf(flagValue));
            if (!success)   {
                player.sendMessage("Flag '" + flagName + "' nao existe!");
                return true;
            }

            player.sendMessage("Flag '" + flagName + "' foi alterada para '" + flagValue + "' na area '" + areaData.getAreaName() + "'.");
            return true;
        }

        return false;
    }
}
