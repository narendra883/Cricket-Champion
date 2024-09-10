package com.app.cricketchampion.controller;

import com.app.cricketchampion.entity.Player;
import com.app.cricketchampion.entity.Team;
import com.app.cricketchampion.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    //this endpoint return  All teams present in the database
    @GetMapping("/")
    public ResponseEntity<List<Team>> getAllTeams(){
        return ResponseEntity.ok(teamRepository.findAll());
    }

    //return a specific team according to the team id
    @GetMapping("/{teamId}")
    public ResponseEntity<?> getTeamById(@PathVariable Integer teamId){
        Optional<Team> teamOpt = teamRepository.findById(teamId);
        if(!teamOpt.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The team Id "+teamId+" not found");
        }
        return ResponseEntity.ok(teamOpt.get());

    }

    @PostMapping("/createTeam")
    public ResponseEntity<Team> createTeam(Team team){
        return ResponseEntity.ok(teamRepository.save(team));
    }


}
