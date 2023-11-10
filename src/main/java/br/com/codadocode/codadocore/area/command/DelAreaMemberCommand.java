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

public class DelAreaMemberCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player)commandSender;
            Vector3 playerPosition = ConvertUtility.locationToVector3(player.getLocation());

            if (strings.length != 1) return false;
            String memberName = strings[0];
            AreaManager manager = AreaManager.getInstance();

            AreaInfo areaInfo = manager.checkVector3InsideArea(playerPosition);
            Optional<AreaData> optAreaData = areaInfo.getSubArea().isPresent() ? areaInfo.getSubArea() : areaInfo.getMainArea();
            Optional<Player> optFindedPlayer = ServerUtility.getPlayerByName(memberName);
            if (optAreaData.isEmpty())   {
                player.sendMessage("Você não está dentro de nenhuma area!");
                return true;
            }

            if (optFindedPlayer.isEmpty())   {
                player.sendMessage("Jogador '" + memberName + "' nao esta online ou nao existe!");
                return true;
            }

            AreaData areaData = optAreaData.get();
            Player memberPlayer = optFindedPlayer.get();
            boolean success = areaData.removeMember(player, memberPlayer);

            if (success)   player.sendMessage("Jogador '" + memberName + "' nao e mais membro da area '" + areaData.getAreaName() + "'.");
            else   player.sendMessage("Você nao tem permissao para remover membro dessa regiao!");

            return true;
        }

        return false;
    }
}
