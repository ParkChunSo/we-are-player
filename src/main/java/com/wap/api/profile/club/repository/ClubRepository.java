package com.wap.api.profile.club.repository;

import com.wap.api.profile.domain.entitys.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByClubNameAndCityAndDistrictAndDeleteFlagFalse(String clubName,  String city, String district);
    Optional<List<Club>> findByClubName(String clubName);
    Optional<List<Club>> findByCityAndDistrict(String city, String district);
    boolean existsByClubNameAndCityAndDistrict(String clubName, String city, String district);
}
