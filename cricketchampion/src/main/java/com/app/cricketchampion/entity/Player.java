package com.app.cricketchampion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long player_id;
    private String playerName;
    private String playerRole;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stats_id",referencedColumnName ="statsId")
    private PlayerStatistics stats;

    @ManyToOne
    @JoinColumn(name="team_id")
    @JsonIgnore
    private Team team;


}
