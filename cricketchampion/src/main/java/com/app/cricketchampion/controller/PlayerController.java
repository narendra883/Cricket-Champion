package com.app.cricketchampion.controller;

import com.app.cricketchampion.entity.Player;
import com.app.cricketchampion.entity.PlayerStatistics;
import com.app.cricketchampion.entity.Team;
import com.app.cricketchampion.repository.PlayerRepository;
import com.app.cricketchampion.repository.PlayerStatisticsRepository;
import com.app.cricketchampion.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerStatisticsRepository playerStatisticsRepository;

    @PostMapping("/createPlayer")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player){
        player.setStats(new PlayerStatistics());
        return ResponseEntity.ok(playerRepository.save(player));
    }


    @GetMapping("/getAllPlayers")
    public ResponseEntity<List<Player>> getAllPlayers(){
        return ResponseEntity.ok(playerRepository.findAll());
    }


    @PutMapping("/updatePlayer/{playerId}")
    public ResponseEntity<Player> updatePlayerById(@PathVariable Long playerId,
                                                   @RequestBody Player player){
        player.setPlayer_id(playerId);
        return ResponseEntity.ok(playerRepository.save(player));
    }

    @GetMapping("/getPlayerById/{playerId}")
    public ResponseEntity<?> getPlayerById(@PathVariable Long playerId){
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if(!playerOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player with ID"+playerId+"Not Found");
        }
        return ResponseEntity.ok(playerOptional.get());
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<Optional<Team>> getPlayersBySpecificTeam(@PathVariable Integer teamId){
        return ResponseEntity.ok(teamRepository.findById(teamId));
    }

    @PostMapping("/{playerId}/stats")
    public ResponseEntity<String> addOrUpdatePlayerStats(@PathVariable Long playerId,
                                                         @RequestBody PlayerStatistics stats) {
        // Find the player by ID
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (!playerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found with ID: " + playerId);
        }

        // Get the player and update the stats
        Player player = playerOptional.get();
        player.setStats(stats);  // Set the stats for the player
        playerStatisticsRepository.save(stats);  // Save or update player statistics
        playerRepository.save(player);  // Update player with new stats

        return ResponseEntity.ok("Player statistics updated successfully for player ID: " + playerId);
    }

}
