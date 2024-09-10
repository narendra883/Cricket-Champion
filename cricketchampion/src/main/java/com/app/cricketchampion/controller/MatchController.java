package com.app.cricketchampion.controller;

import com.app.cricketchampion.entity.Matches;
import com.app.cricketchampion.entity.Player;
import com.app.cricketchampion.entity.PlayerStatistics;
import com.app.cricketchampion.entity.Team;
import com.app.cricketchampion.repository.MatchRepository;
import com.app.cricketchampion.repository.PlayerRepository;
import com.app.cricketchampion.repository.PlayerStatisticsRepository;
import com.app.cricketchampion.repository.TeamRepository;
import com.app.cricketchampion.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerRepository playerRepository;



    @PostMapping("/create")
    public ResponseEntity<Matches> createMatch(@RequestBody Matches match) {
        return ResponseEntity.ok(matchRepository.save(match));
    }

    @PostMapping("/{matchId}/createTeam/{teamPosition}")
    public ResponseEntity<?> createMatchTeam(@PathVariable Integer matchId,
                                             @PathVariable String teamPosition,
                                             @RequestBody Team team) {
        Optional<Matches> matchOpt = matchRepository.findById(matchId);
        if (!matchOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Match not found with ID: " + matchId);
        }

        Matches match = matchOpt.get();

        Team teamToAdd = teamRepository.save(team);

        // Set the team to the appropriate position (team1 or team2)
        if (teamPosition.equalsIgnoreCase("team1")) {
            if (match.getTeam1() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Team1 is already set for this match.");
            }
            match.setTeam1(teamToAdd);
        } else if (teamPosition.equalsIgnoreCase("team2")) {
            if (match.getTeam2() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Team2 is already set for this match.");
            }
            match.setTeam2(teamToAdd);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid team position. Use 'team1' or 'team2'.");
        }

        Matches updatedMatch = matchRepository.save(match);
        return ResponseEntity.ok(updatedMatch);
    }


    @GetMapping("/{matchId}/createTeam/{teamId}")
    public ResponseEntity<?> getMatchByTeamId(@PathVariable Integer matchId,
                                              @PathVariable Integer teamId) {
        Optional<Matches> matchOpt = matchRepository.findById(matchId);
        if (!matchOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Match not found with ID: " + matchId);
        }

        Matches match = matchOpt.get();

        if ((match.getTeam1() != null && match.getTeam1().getTeamId().equals(teamId)) ||
                (match.getTeam2() != null && match.getTeam2().getTeamId().equals(teamId))) {
            return ResponseEntity.ok(match);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Team not found with ID: " + teamId + " in match ID: " + matchId);
        }
    }

    @PostMapping("/{matchId}/createTeam/{teamId}/createPlayers")
    public ResponseEntity<?> createPlayers(@PathVariable Integer matchId,
                                           @PathVariable Integer teamId,
                                           @RequestBody List<Player> players) {
        // Fetch the match by ID
        Optional<Matches> matchOpt = matchRepository.findById(matchId);
        if (!matchOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Match not found with ID: " + matchId);
        }

        Matches match = matchOpt.get();

        // Fetch the team by ID
        Optional<Team> teamOpt = teamRepository.findById(teamId);
        if (!teamOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Team not found with ID: " + teamId);
        }

        Team team = teamOpt.get();

        // Check if the team is part of the match
        boolean isTeamPartOfMatch = (match.getTeam1() != null && match.getTeam1().getTeamId().equals(teamId)) ||
                (match.getTeam2() != null && match.getTeam2().getTeamId().equals(teamId));

        if (!isTeamPartOfMatch) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The team is not part of the match.");
        }

        // Initialize players list if it's null (optional, if your implementation allows null lists)
        if (team.getPlayers() == null){
            team.setPlayers(new ArrayList<>());
        }

        try {
            team = teamService.addPlayersToTeam(team, players);
            // Save the updated team with new players
            teamRepository.save(team);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.ok(team.getPlayers());
    }



    // geting all matches
    @GetMapping("/getAllMatches")
    public ResponseEntity<List<Matches>> getAllMatches(){
        return ResponseEntity.ok(matchRepository.findAll());
    }


}
