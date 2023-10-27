package br.com.codadocode.codadocore.area.command;

import br.com.codadocode.codadocore.area.AreaData;
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

            if (strings.length != 3) return false;

            String areaName = strings[0];
            String flagName = strings[1].toUpperCase();
            String flagValue = strings[2].toLowerCase();

            if (!flagValue.equals("true") && !flagValue.equals("false"))   {
                player.sendMessage("Apenas valores 'true' e 'false' podem ser usados nas flags!");
                return true;
            }

            AreaManager areaManager = AreaManager.getInstance();
            Optional<AreaData> optAreaData = areaManager.getAreaByName(areaName);
            if (optAreaData.isEmpty()){
                player.sendMessage("Area '" + areaName + "' nao existe!");
                return true;
            }

            AreaData areaData = optAreaData.get();
            boolean success = areaData.setFlagValue(flagName, Boolean.valueOf(flagValue));
            if (!success)   {
                player.sendMessage("Flag '" + flagName + "' nao existe!");
                return true;
            }

            player.sendMessage("Flag '" + flagName + "' foi alterada para '" + flagValue + "' na area '" + areaName + "'.");
            return true;
        }

        return false;
    }
}
