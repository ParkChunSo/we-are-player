package com.chun.crud.repository;

import com.chun.crud.entitys.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
