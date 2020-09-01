package com.chun.crud.repository;

import com.chun.crud.entity.Match;
import com.chun.crud.entitys.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MatchCrudRepository extends JpaRepository<Match, Long> {
    Optional<List<Match>> findByHomeClubOrAwayClubAndDateBetween(Club homeClub, Club awayClub, LocalDateTime from, LocalDateTime to);
    Optional<List<Match>> findByCityAndDistrictAndDateBetween(String city, String district, LocalDateTime to, LocalDateTime from);
    Optional<List<Match>> findByHomeClubOrAwayClub(Club homeClub, Club awayClub);
}
