package com.wap.api.match.repository;

import com.wap.api.profile.domain.entitys.Club;
import com.wap.api.profile.domain.entitys.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<List<Match>> findByHomeClubOrAwayClubAndDateBetween(Club homeClub, Club awayClub, LocalDateTime from, LocalDateTime to);
    Optional<List<Match>> findByCityAndDistrictAndDateBetween(String city, String district, LocalDateTime to, LocalDateTime from);
    Optional<List<Match>> findByHomeClubOrAwayClub(Club homeClub, Club awayClub);
}
