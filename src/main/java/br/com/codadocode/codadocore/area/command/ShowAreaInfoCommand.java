package br.com.codadocode.codadocore.area.command;
import br.com.codadocode.codadocore.area.AreaData;
import br.com.codadocode.codadocore.area.AreaInfo;
import br.com.codadocode.codadocore.area.AreaManager;
import br.com.codadocode.codadocore.core.CodadoLog;
import br.com.codadocode.codadocore.core.ConvertUtility;
import br.com.codadocode.codadocore.core.Vector3;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ShowAreaInfoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player)commandSender;
            AreaManager manager = AreaManager.getInstance();
            Vector3 playerPosition = ConvertUtility.locationToVector3(player.getLocation());

            AreaInfo areaInfo = manager.checkVector3InsideArea(playerPosition);
            Optional<AreaData> optAreaData = areaInfo.getSubArea().isPresent() ? areaInfo.getSubArea() : areaInfo.getMainArea();
            if (optAreaData.isEmpty())   {
                player.sendMessage("Voce não está dentro de nenhuma região!");
                return true;
            }

            AreaData areaData = optAreaData.get();
            String areaInfoMsg = "Você esta dentro da area: " + areaData.getAreaName() + ", Dono: " + areaData.getOwner();
            player.sendMessage(areaInfoMsg);
            return true;
        }

        return false;
    }
}
