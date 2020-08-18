package com.wap.api.profile.club;

import com.wap.api.common.RepositoryTest;
import com.wap.api.profile.domain.builder.ClubMemberBuilder;
import com.wap.api.profile.domain.entitys.Club;
import com.wap.api.profile.domain.entitys.ClubMember;
import com.wap.api.profile.domain.entitys.Member;
import com.wap.api.profile.domain.enums.ClubMemberType;
import com.wap.api.profile.domain.request.ClubInfoSetUp;
import com.wap.api.profile.domain.request.MemberInfoSetUp;
import com.wap.api.profile.club.repository.ClubMemberRepository;
import com.wap.api.profile.club.repository.ClubRepository;
import com.wap.api.profile.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
public class ClubMemberRepositoryTest {
    @Autowired
    ClubMemberRepository clubMemberRepository;
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("클럽 멤버 저장 성공")
    void testSaveClubMemberSuccess(){
        //given
        Member memberKim = memberRepository.saveAndFlush(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim));
        Club club = clubRepository.saveAndFlush(new Club(ClubInfoSetUp.yangpyeongFC));

        //when
        ClubMember save = clubMemberRepository.saveAndFlush(ClubMemberBuilder.buildMember(memberKim, club));

        //then
        assertNotNull(save.getId());
        assertEquals(save.getClub(), club);
        assertEquals(save.getMember(), memberKim);
    }

    @Test
    @DisplayName("클럽 멤버 조회 성공")
    void testFindClubAndClubMemberTypeSuccess(){
        //given
        final int LIST_SIZE = 2;
        Member leader = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park));
        Member memberKim = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim));
        Member memberJeoung = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.jeoung));
        Club club = clubRepository.save(new Club(ClubInfoSetUp.yangpyeongFC));
        clubMemberRepository.saveAll(Arrays.asList(
                ClubMemberBuilder.buildLeader(leader, club),
                ClubMemberBuilder.buildMember(memberJeoung, club),
                ClubMemberBuilder.buildMember(memberKim, club)
        ));

        //when
        List<ClubMember> save = clubMemberRepository.findByClubAndClubMemberType(club, ClubMemberType.MEMBER)
                .orElse(Collections.emptyList());

        //then
        assertFalse(save.isEmpty());
        assertEquals(save.size(), LIST_SIZE);
    }
    @Test
    @DisplayName("클럽 멤버 조회 성공")
    void testFindByMemberAndClubMemberTypeSuccess(){
        //TODO("JPQL 고려")
    }

    @Test
    @DisplayName("클럽 멤버 조회 성공")
    void testFindByClubNameAndClubLocationSuccess(){
        //given
        final String clubName = "양평FC";
        final String clubCity = "경기도";
        final String clubDistrict = "양평군";

        Member park = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park));
        Member memberKim = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim));
        Member memberJeoung = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.jeoung));
        Member memberYun = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.yun));

        Club yangpyeongClub = clubRepository.save(new Club(ClubInfoSetUp.yangpyeongFC));
        Club seoulClub = clubRepository.save(new Club(ClubInfoSetUp.seoulFC));

        clubMemberRepository.saveAll(Arrays.asList(
                ClubMemberBuilder.buildLeader(park, yangpyeongClub),
                ClubMemberBuilder.buildMember(memberJeoung, yangpyeongClub),
                ClubMemberBuilder.buildLeader(memberKim, seoulClub),
                ClubMemberBuilder.buildMember(memberYun, seoulClub)
        ));


        //when
        List<ClubMember> clubMembers = clubMemberRepository.findByClub_ClubNameAndClub_CityAndClub_District(clubName, clubCity, clubDistrict)
                .orElse(Collections.emptyList());


        //then
        assertFalse(clubMembers.isEmpty());
        assertEquals(clubMembers.size(), 2);
        for (ClubMember clubMember : clubMembers) {
            assertEquals(clubMember.getClub().getClubName(), clubName);
            assertEquals(clubMember.getClub().getCity(), clubCity);
            assertEquals(clubMember.getClub().getDistrict(), clubDistrict);
        }
    }

    @Test
    @DisplayName("클럽 멤버 조회 성공")
    void testFindByClubNameAndClubLocationAndClubMemberTypeSuccess(){
        //given
        final String clubName = "양평FC";
        final String clubCity = "경기도";
        final String clubDistrict = "양평군";

        Member park = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park));
        Member memberKim = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim));
        Member memberJeoung = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.jeoung));
        Member memberYun = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.yun));

        Club yangpyeongClub = clubRepository.save(new Club(ClubInfoSetUp.yangpyeongFC));
        Club seoulClub = clubRepository.save(new Club(ClubInfoSetUp.seoulFC));

        clubMemberRepository.saveAll(Arrays.asList(
                ClubMemberBuilder.buildLeader(park, yangpyeongClub),
                ClubMemberBuilder.buildMember(memberJeoung, yangpyeongClub),
                ClubMemberBuilder.buildMember(memberYun, yangpyeongClub),
                ClubMemberBuilder.buildLeader(memberKim, seoulClub),
                ClubMemberBuilder.buildMember(memberYun, seoulClub)
        ));

        //when
        List<ClubMember> leaders = clubMemberRepository.findByClub_ClubNameAndClub_CityAndClub_DistrictAndClubMemberType(clubName, clubCity, clubDistrict, ClubMemberType.LEADER)
                .orElse(Collections.emptyList());
        List<ClubMember> members = clubMemberRepository.findByClub_ClubNameAndClub_CityAndClub_DistrictAndClubMemberType(clubName, clubCity, clubDistrict, ClubMemberType.MEMBER)
                .orElse(Collections.emptyList());

        //then
        assertFalse(leaders.isEmpty());
        assertFalse(members.isEmpty());
        assertEquals(leaders.size(), 1);
        assertEquals(members.size(), 2);
        for (ClubMember clubMember : leaders) {
            assertEquals(clubMember.getClub().getClubName(), clubName);
            assertEquals(clubMember.getClub().getCity(), clubCity);
            assertEquals(clubMember.getClub().getDistrict(), clubDistrict);
            assertEquals(clubMember.getClubMemberType(), ClubMemberType.LEADER);
        }
        for (ClubMember clubMember : members) {
            assertEquals(clubMember.getClub().getClubName(), clubName);
            assertEquals(clubMember.getClub().getCity(), clubCity);
            assertEquals(clubMember.getClub().getDistrict(), clubDistrict);
            assertEquals(clubMember.getClubMemberType(), ClubMemberType.MEMBER);
        }
    }
}
