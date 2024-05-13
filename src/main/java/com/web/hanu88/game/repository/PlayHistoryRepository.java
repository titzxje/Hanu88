package com.web.hanu88.game.repository;

import com.web.hanu88.game.model.PlayHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayHistoryRepository extends JpaRepository<PlayHistory, Long> {
}
