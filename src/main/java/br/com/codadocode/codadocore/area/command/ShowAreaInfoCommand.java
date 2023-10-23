package br.com.codadocode.codadocore.area.command;
import br.com.codadocode.codadocore.area.AreaManager;
import br.com.codadocode.codadocore.core.CodadoLog;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ShowAreaInfoCommand implements CommandExecutor {

    private CodadoLog log;

    public ShowAreaInfoCommand()   {
        this.log = new CodadoLog("AreaInfo");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player)commandSender;
            AreaManager manager = (AreaManager)AreaManager.getInstance();
        }

        return false;
    }
}
