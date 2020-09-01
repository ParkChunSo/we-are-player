package com.chun.crud.repository;

import com.chun.crud.entitys.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCrudRepository extends JpaRepository<Member, String> {
}
