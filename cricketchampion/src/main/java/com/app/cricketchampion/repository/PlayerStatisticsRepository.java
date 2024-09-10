package com.app.cricketchampion.repository;

import com.app.cricketchampion.entity.Matches;
import com.app.cricketchampion.entity.Player;
import com.app.cricketchampion.entity.PlayerStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStatisticsRepository extends JpaRepository<PlayerStatistics, Integer> {


}
