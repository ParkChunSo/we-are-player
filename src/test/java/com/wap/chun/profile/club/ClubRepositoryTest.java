package com.wap.chun.profile.club;

import com.wap.chun.common.RepositoryTest;
import com.wap.chun.domain.builder.ClubBuilder;
import com.wap.chun.domain.builder.ClubMemberBuilder;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.request.ClubInfoUpdateDtoSetUp;
import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.request.MemberInfoSetUp;
import com.wap.chun.profile.club.dtos.ClubInfoUpdateDto;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
public class ClubRepositoryTest {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Test
    @DisplayName("클럽 저장(성공)")
    void testSaveClubSuccess(){
        //given
        Club club = ClubBuilder.yangpyeongFC;

        //when
        Club save = clubRepository.saveAndFlush(club);

        //then
        assertNotNull(save.getClubId());
    }
    
    @Test
    @DisplayName("클럽 조회(성공)")
    void testGetClubSuccess(){
        //given
        Club club = ClubBuilder.yangpyeongFC;
        Member member = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        clubRepository.saveAndFlush(club);
        memberRepository.saveAndFlush(member);
        clubMemberRepository.saveAndFlush(ClubMemberBuilder.buildLeader(member, club));

        //when
        Optional<Club> save = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(club.getClubName(), club.getCity(), club.getDistrict());

        //then
        assertTrue(save.isPresent());
        assertNotNull(save.get().getClubId());
        assertEquals(club.getClubName(), save.get().getClubName());
        assertEquals(club.getCity(), save.get().getCity());
        assertEquals(club.getDistrict(), save.get().getDistrict());
        assertEquals(club.getClubMembers().size(), 1);
    }

    @Test
    @DisplayName("클럽 중복 확인(성공)")
    void testExistsByClubNameAndLocationSuccess(){
        //given
        Club YClub = ClubBuilder.yangpyeongFC;
        clubRepository.saveAndFlush(YClub);

        //when
        boolean val = clubRepository.existsByClubNameAndCityAndDistrict(YClub.getClubName(), YClub.getCity(), YClub.getDistrict());

        //then
        assertTrue(val);
    }

    @Test
    @DisplayName("클럽 지역명으로 조회(성공)")
    void testFindByLocationSuccess(){
        //given
        Club YClub = ClubBuilder.yangpyeongFC;
        Club YPClub = ClubBuilder.build("양평프로FC");
        clubRepository.saveAndFlush(YClub);
        clubRepository.saveAndFlush(YPClub);

        //when
        Optional<List<Club>> save = clubRepository.findByCityAndDistrict(YClub.getCity(), YClub.getDistrict());

        //then
        assertTrue(save.isPresent());
        assertEquals(save.get().size(), 2);
    }

    @Test
    @DisplayName("클럽명으로 조회(성공)")
    void testFindByClubNameSuccess(){
        //given
        Club YClub = ClubBuilder.build("잔디밭");
        Club YPClub = ClubBuilder.build("잔디밭");
        clubRepository.saveAndFlush(YClub);
        clubRepository.saveAndFlush(YPClub);

        //when
        Optional<List<Club>> save = clubRepository.findByCityAndDistrict(YClub.getCity(), YClub.getDistrict());

        //then
        assertTrue(save.isPresent());
        assertEquals(save.get().size(), 2);
    }

    @Test
    @DisplayName("정보 업데이트(성공)")
    void testUpdateClub(){
        //given
        ClubInfoUpdateDto dto = ClubInfoUpdateDtoSetUp.dto;
        Club club = ClubBuilder.yangpyeongFC;
        club = clubRepository.saveAndFlush(club);

        //when
        club.setLikeCnt(dto.getLikeCnt());
        club.setRudeCnt(dto.getRudeCnt());
        Club save = clubRepository.saveAndFlush(club);

        //then
        assertNotNull(save.getClubId());
        assertEquals(club.getLikeCnt(), dto.getLikeCnt());
        assertEquals(club.getRudeCnt(), dto.getRudeCnt());
    }
}
