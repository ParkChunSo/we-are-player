package com.chun.modules.crud.club.repository;

import com.chun.modules.crud.club.entitys.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubCrudRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByClubNameAndCityAndDistrict(String clubName, String city, String district);
    Optional<List<Club>> findByClubName(String clubName);
    Optional<List<Club>> findByCityAndDistrict(String city, String district);
    boolean existsByClubNameAndCityAndDistrict(String clubName, String city, String district);
}
