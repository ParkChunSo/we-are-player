package com.chun.crud.repository;

import com.chun.crud.entitys.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubCrudRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByClubNameAndCityAndDistrict(String clubName, String city, String district);
    Optional<List<Club>> findByClubName(String clubName);
    Optional<List<Club>> findByCityAndDistrict(String city, String district);
    boolean existsByClubNameAndCityAndDistrict(String clubName, String city, String district);
}
