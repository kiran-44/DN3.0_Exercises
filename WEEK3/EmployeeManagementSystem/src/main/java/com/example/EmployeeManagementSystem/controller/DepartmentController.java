package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.model.Team; 
import com.example.employeemanagementsystem.services.TeamService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams") 
public class TeamController {

    @Autowired
    private TeamService teamService; 

    @GetMapping
    public List<Team> getAllTeams() { 
        return teamService.getAllTeams(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) { 
        return teamService.getTeamById(id) 
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) { // Updated method and parameter
        return teamService.saveTeam(team); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team teamDetails) { 
        return ResponseEntity.ok(teamService.updateTeam(id, teamDetails)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) { 
        teamService.deleteTeam(id); 
        return ResponseEntity.noContent().build();
    }
}
