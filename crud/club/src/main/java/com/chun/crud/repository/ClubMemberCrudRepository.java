package com.chun.crud.repository;

import com.chun.commons.enums.ClubMemberType;
import com.chun.crud.entitys.Club;
import com.chun.crud.entitys.ClubMember;
import com.chun.crud.entitys.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubMemberCrudRepository extends JpaRepository<ClubMember, Long> {
    Optional<ClubMember> findByClubAndMemberAndClubMemberType(Club club, Member member, ClubMemberType type);
    Optional<List<ClubMember>> findByClubAndClubMemberType(Club club, ClubMemberType type);
    Optional<List<ClubMember>> findByMemberAndClubMemberType(Member member, ClubMemberType type);
    void deleteAllByClubAndMember(Club club, Member member);
}