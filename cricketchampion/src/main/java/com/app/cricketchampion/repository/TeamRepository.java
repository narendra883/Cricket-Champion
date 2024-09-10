package com.app.cricketchampion.repository;

import com.app.cricketchampion.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
