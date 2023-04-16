package br.com.codadocode.codadocore.worldspawn.command;

import br.com.codadocode.codadocore.worldspawn.WorldSpawnManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player) commandSender;
            Location playerLocation = player.getLocation();
            World actualWorld = player.getWorld();
            //WorldSpawnManager.getInstance().
        }
        return true;
    }
}
