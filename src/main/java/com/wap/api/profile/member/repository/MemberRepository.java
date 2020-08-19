package com.wap.api.profile.member.repository;

import com.wap.api.domain.entitys.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
