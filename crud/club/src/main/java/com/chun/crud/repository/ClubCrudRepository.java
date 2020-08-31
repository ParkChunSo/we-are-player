package com.chun.crud.repository;

import com.chun.crud.entitys.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubCrudRepository extends JpaRepository<Club, Long> {
}
