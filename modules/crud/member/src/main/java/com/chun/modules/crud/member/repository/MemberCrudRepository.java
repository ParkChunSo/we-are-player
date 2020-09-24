package com.chun.modules.crud.member.repository;

import com.chun.modules.crud.member.entitys.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCrudRepository extends JpaRepository<Member, String> {
}
