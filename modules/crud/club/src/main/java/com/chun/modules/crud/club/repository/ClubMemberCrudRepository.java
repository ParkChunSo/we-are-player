package com.chun.modules.crud.club.repository;

import com.chun.commons.enums.ClubMemberType;
import com.chun.modules.crud.club.entitys.ClubMember;
import com.chun.modules.crud.club.entitys.Club;
import com.chun.modules.crud.member.entitys.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubMemberCrudRepository extends JpaRepository<ClubMember, Long> {
    Optional<ClubMember> findByClubAndMember(Club club, Member member);
    Optional<List<ClubMember>> findByClubAndClubMemberType(Club club, ClubMemberType type);
    Optional<List<ClubMember>> findByMemberAndClubMemberType(Member member, ClubMemberType type);
    void deleteAllByClubAndMember(Club club, Member member);
}
