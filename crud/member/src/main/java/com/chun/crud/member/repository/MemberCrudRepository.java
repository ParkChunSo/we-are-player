package com.chun.crud.member.repository;

import com.chun.crud.member.entitys.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCrudRepository extends JpaRepository<Member, String> {
}
