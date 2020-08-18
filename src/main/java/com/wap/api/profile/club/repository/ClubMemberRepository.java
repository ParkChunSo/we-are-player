package com.wap.api.profile.club.repository;

import com.wap.api.profile.domain.entitys.Club;
import com.wap.api.profile.domain.entitys.ClubMember;
import com.wap.api.profile.domain.entitys.Member;
import com.wap.api.profile.domain.enums.ClubMemberType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
    Optional<List<ClubMember>> findByClub_ClubNameAndClub_CityAndClub_District(String clubName, String city, String district);
    Optional<List<ClubMember>> findByClub_ClubNameAndClub_CityAndClub_DistrictAndClubMemberType(String clubName,  String city, String district, ClubMemberType type);
    Optional<List<ClubMember>> findByClubAndClubMemberType(Club club, ClubMemberType type);
    Optional<List<ClubMember>> findByMemberAndClubMemberType(Member member, ClubMemberType type);
}
