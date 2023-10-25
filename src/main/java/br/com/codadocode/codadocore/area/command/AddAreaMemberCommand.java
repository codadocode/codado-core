package br.com.codadocode.codadocore.area.command;

import br.com.codadocode.codadocore.area.AreaData;
import br.com.codadocode.codadocore.area.AreaManager;
import br.com.codadocode.codadocore.core.ServerUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class AddAreaMemberCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)   {
            Player player = (Player)commandSender;

            if (strings.length != 2) return false;
            String areaName = strings[0];
            String memberName = strings[1];
            AreaManager manager = AreaManager.getInstance();

            Optional<AreaData> optAreaData = manager.getAreaByName(areaName);
            Optional<Player> optFindedPlayer = ServerUtility.getPlayerByName(memberName);
            if (optAreaData.isEmpty())   {
                player.sendMessage("Area '" + areaName + "' nao existe!");
                return true;
            }

            if (optFindedPlayer.isEmpty())   {
                player.sendMessage("Jogador '" + memberName + "' nao esta online ou nao existe!");
                return true;
            }

            AreaData areaData = optAreaData.get();
            Player memberPlayer = optFindedPlayer.get();
            boolean success = areaData.addMember(player, memberPlayer);

            if (success)   player.sendMessage("Jogador '" + memberName + "' agora e membro da area '" + areaData.getAreaName() + "'.");
            else   player.sendMessage("Você nao tem permissao para adicionar membro nessa regiao!");

            return true;
        }

        return false;
    }
}
