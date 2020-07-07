package com.wap.chun.profile.member.repository;

import com.wap.chun.domain.entitys.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
