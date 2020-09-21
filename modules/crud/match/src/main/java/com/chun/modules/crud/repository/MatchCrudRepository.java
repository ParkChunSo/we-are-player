package com.chun.modules.crud.repository;

import com.chun.modules.crud.entity.Match;
import com.chun.modules.crud.club.entitys.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MatchCrudRepository extends JpaRepository<Match, Long> {
    Optional<List<Match>> findByHomeClubOrAwayClubAndDateBetween(Club homeClub, Club awayClub, LocalDateTime from, LocalDateTime to);
    Optional<List<Match>> findByCityAndDistrictAndDateBetween(String city, String district, LocalDateTime to, LocalDateTime from);
    Optional<List<Match>> findByHomeClubOrAwayClub(Club homeClub, Club awayClub);
    Optional<List<Match>> findByHomeClubOrAwayClubOrHomeClubOrAwayClub(Club homeClub1, Club awayClub1, Club homeClub2, Club awayClub2);

}
