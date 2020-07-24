package com.wap.chun.profile.club;

import com.wap.chun.common.ServiceTest;
import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.request.ClubInfoSetUp;
import com.wap.chun.domain.request.MemberInfoSetUp;
import com.wap.chun.error.exception.ClubAlreadyExistException;
import com.wap.chun.error.exception.MemberNotFoundException;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.club.service.ClubService;
import com.wap.chun.profile.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ServiceTest
public class ClubServiceTest {
    @InjectMocks
    ClubService clubService;

    @Mock
    ClubRepository clubRepository;

    @Mock
    MemberRepository memberRepository;

    @Test
    @DisplayName("클럽 중복 오류(실패)")
    void testCreateClubFailByDuplication(){
        //given
        given(clubRepository.existsByClubNameAndLocation(anyString(), anyString())).willReturn(true);

        //then
        assertThrows(ClubAlreadyExistException.class, () -> clubService.createClub(ClubInfoSetUp.yangpyeongFC));
    }

    @Test
    @DisplayName("리더 NPE 오류(실패)")
    void testCreateClubFailByLeaderNotFound(){
        //given
        given(clubRepository.existsByClubNameAndLocation(anyString(), anyString())).willReturn(false);
        given(memberRepository.findById(anyString())).willReturn(Optional.empty());

        //then
        assertThrows(MemberNotFoundException.class, () -> clubService.createClub(ClubInfoSetUp.yangpyeongFC));
    }

    @Test
    @DisplayName("클럽 조회(성공)")
    void testGetClubInfoSuccess(){
        //given
        ClubInfoDto dto = ClubInfoSetUp.yangpyeongFC;
        given(clubRepository.findByClubNameAndLocation(anyString(), anyString()))
                .willReturn(Optional.of(new Club(dto, MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park))));

        //when
        ClubInfoDto clubInfo = clubService.getClubInfo(dto.getClubName(), dto.getLocation());

        //then
        assertEquals(clubInfo.getLeaderId(), dto.getLeaderId());
        assertEquals(clubInfo.getClubName(), dto.getClubName());
        assertEquals(clubInfo.getLocation(), dto.getLocation());
    }

    @Test
    @DisplayName("클럽 이름으로 조회")
    void testFindByClubName(){
        //given
        String clubName = "양평FC";
        Member leader = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        List<Club> list = Arrays.asList(new Club(ClubInfoSetUp.yangpyeongFC, leader), new Club(ClubInfoSetUp.seoulYangpyeongFC, leader));
        given(clubRepository.findByClubName(anyString())).willReturn(Optional.of(list));

        //when
        List<ClubInfoDto> result = clubService.findByClubName(clubName);

        //then
        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("클럽 지역으로 조회")
    void testFindByLocation(){
        //given
        String clubLocation = "경기도 양평";
        Member leader = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        List<Club> list = Arrays.asList(new Club(ClubInfoSetUp.yangpyeongFC, leader), new Club(ClubInfoSetUp.yangpyeongProFC, leader));

        given(clubRepository.findByLocation(anyString())).willReturn(Optional.of(list));

        //when
        List<ClubInfoDto> result = clubService.findByLocation(clubLocation);

        //then
        assertEquals(result.size(), 2);
    }
}
