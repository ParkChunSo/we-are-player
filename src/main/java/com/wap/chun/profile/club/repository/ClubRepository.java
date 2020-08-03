package com.wap.chun.profile.club.repository;

import com.wap.chun.domain.entitys.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByClubNameAndLocationAndDeleteFlagFalse(String clubName, String clubLocation);
    Optional<List<Club>> findByClubName(String clubName);
    Optional<List<Club>> findByLocation(String clubLocation);
    boolean existsByClubNameAndLocation(String clubName, String clubLocation);

    void deleteByClubNameAndLocation(String clubName, String clubLocation);
}
