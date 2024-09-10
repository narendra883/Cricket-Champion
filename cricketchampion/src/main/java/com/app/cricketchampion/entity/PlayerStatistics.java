package com.app.cricketchampion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playerStats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statsId;



    private int runs=0;
    private int wickets=0;
    private int matchesPlayed=0;
    private int catches=0;
    private int sixes=0;
    private int fours=0;
}
