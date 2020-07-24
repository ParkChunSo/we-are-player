package com.wap.chun.profile.club;

import com.wap.chun.common.RepositoryTest;
import com.wap.chun.domain.builder.ClubMemberBuilder;
import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.request.ClubInfoSetUp;
import com.wap.chun.domain.request.MemberInfoSetUp;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    @DisplayName("")
    void testSaveClubMember(){
        //given
        Member leader = memberRepository.saveAndFlush(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park));
        Member memberKim = memberRepository.saveAndFlush(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim));
        Club club = clubRepository.saveAndFlush(new Club(ClubInfoSetUp.yangpyeongFC, leader));

        //when
        ClubMember save = clubMemberRepository.saveAndFlush(ClubMemberBuilder.build(memberKim, club));

        //then
        assertNotNull(save.getId());
        assertEquals(save.getClub(), club);
        assertEquals(save.getMember(), memberKim);
    }

    @Test
    @DisplayName("")
    void testFindClubMember(){
        //given
        final int LIST_SIZE = 3;
        Member leader = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park));
        Member memberKim = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim));
        Member memberJeoung = memberRepository.save(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.jeoung));
        Club club = clubRepository.save(new Club(ClubInfoSetUp.yangpyeongFC, leader));
        clubMemberRepository.save(ClubMemberBuilder.build(memberJeoung, club));
        clubMemberRepository.save(ClubMemberBuilder.build(memberKim, club));

        //when
//        List<ClubMember> save =
//                clubMemberRepository.findByClub_ClubNameAndClub_LocationAndClubMemberType(club.getClubName(), club.getLocation(), ClubMemberType.MEMBER)
//                .orElse(Collections.emptyList());
        List<ClubMember> save = clubMemberRepository.findByClubAndClubMemberType(club, ClubMemberType.MEMBER)
                .orElse(Collections.emptyList());

        //then
        assertFalse(save.isEmpty());
        assertEquals(save.size(), LIST_SIZE);
    }
}
