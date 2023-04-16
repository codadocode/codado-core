package br.com.codadocode.codadocore.hidename;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NametagManager {
    public static NametagManager instance;
    private Scoreboard scoreBoard;
    private Team team;
    private final String teamName = "HIDETEAM";

    public NametagManager()   {
        if (NametagManager.instance == null)   NametagManager.instance = this;
        this.scoreBoard = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
        this.team = this.scoreBoard.getTeam(this.teamName);
        if (team == null)   {
            this.team = this.scoreBoard.registerNewTeam(this.teamName);
            this.team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }
    }

    public boolean hidePlayerName(Player player)   {
        if (this.team.hasEntry(player.getName())) return false;

        this.team.addEntry(player.getName());

        return true;
    }

    public boolean unhidePlayerName(Player player)   {
        if (!this.team.hasEntry(player.getName())) return false;

        this.team.removeEntry(player.getName());

        return true;
    }
}
