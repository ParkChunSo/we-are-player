package com.wap.chun.profile.club.repository;

import com.wap.chun.domain.entitys.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
}
