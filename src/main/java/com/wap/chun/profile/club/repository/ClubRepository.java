package com.wap.chun.profile.club.repository;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.profile.club.repository.ClubRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long>, ClubRepositoryCustom {

}
