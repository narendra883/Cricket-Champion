package com.app.cricketchampion.repository;


import com.app.cricketchampion.entity.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Matches, Integer> {

}
