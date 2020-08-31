package com.chun.crud.repository;

import com.chun.crud.entitys.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMemberCrudRepository extends JpaRepository<ClubMember, Long> {
}
