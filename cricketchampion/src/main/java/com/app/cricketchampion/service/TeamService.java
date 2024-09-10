package com.app.cricketchampion.service;

import com.app.cricketchampion.entity.Player;
import com.app.cricketchampion.entity.Team;
import com.app.cricketchampion.repository.PlayerRepository;
import com.app.cricketchampion.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public Team addPlayersToTeam(Team team, List<Player> players) {
        for (Player player : players) {
            if (team.getPlayers().size() >= 12) {
                throw new IllegalArgumentException("A team cannot have more than 12 players.");
            }
            team.getPlayers().add(player);
            System.out.println(player);
            player.setTeam(team);
        }
        return teamRepository.save(team); // Save the team with the new players
    }
}
