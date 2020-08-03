package com.wap.chun.profile.club.repository;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
    Optional<List<ClubMember>> findByClub_ClubNameAndClub_Location(String clubName, String clubLocation);
    Optional<List<ClubMember>> findByClub_ClubNameAndClub_LocationAndClubMemberType(String clubName, String clubLocation, ClubMemberType type);
    Optional<List<ClubMember>> findByClubAndClubMemberType(Club club, ClubMemberType type);
    Optional<List<ClubMember>> findByMemberAndClubMemberType(Member member, ClubMemberType type);
}
