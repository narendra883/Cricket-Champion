package com.app.cricketchampion.repository;

import com.app.cricketchampion.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
