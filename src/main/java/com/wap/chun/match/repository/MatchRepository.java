package com.wap.chun.match.repository;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<List<Match>> findByHomeClubOrAwayClubAndDateBetween(Club homeClub, Club awayClub, LocalDateTime from, LocalDateTime to);
}
