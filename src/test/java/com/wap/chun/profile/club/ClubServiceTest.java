package com.wap.chun.profile.club;

import com.wap.chun.common.ServiceTest;
import com.wap.chun.domain.builder.ClubMemberBuilder;
import com.wap.chun.domain.builder.ClubMemberDtoBuilder;
import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.request.ClubInfoSetUp;
import com.wap.chun.domain.request.MemberInfoSetUp;
import com.wap.chun.error.exception.ClubAlreadyExistException;
import com.wap.chun.error.exception.MemberNotFoundException;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.club.service.ClubService;
import com.wap.chun.profile.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    @Mock
    ClubMemberRepository clubMemberRepository;

    @Test
    @DisplayName("클럽 생성 성공")
    void testCreateClubSuccess() {
        //given
        Club club = new Club(ClubInfoSetUp.yangpyeongFC);
        Member park = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        Member kim = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim);
        Member yun = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.yun);

        ClubInfoDto dto = ClubInfoSetUp.yangpyeongFC;
        dto.setMembers(Arrays.asList(
                ClubMemberDtoBuilder.build(park, ClubMemberType.LEADER),
                ClubMemberDtoBuilder.build(kim, ClubMemberType.MEMBER),
                ClubMemberDtoBuilder.build(yun, ClubMemberType.MEMBER)
        ));


        given(clubRepository.existsByClubNameAndLocation(anyString(), anyString())).willReturn(false);
        given(clubRepository.save(any())).willReturn(club);
        given(memberRepository.findAllById(any()))
                .willReturn(Arrays.asList(park, kim, yun));
        given(clubMemberRepository.saveAll(any())).willReturn(Collections.emptyList());

        //when
        clubService.createClub(dto);
    }

    @Test
    @DisplayName("클럽 중복 오류(실패)")
    void testCreateClubFailByDuplication() {
        //given
        given(clubRepository.existsByClubNameAndLocation(anyString(), anyString())).willReturn(true);

        //then
        assertThrows(ClubAlreadyExistException.class, () -> clubService.createClub(ClubInfoSetUp.yangpyeongFC));
    }

    @Test
    @DisplayName("클럽 조회(성공)")
    void testGetClubInfoSuccess() {
        //given
        ClubInfoDto dto = ClubInfoSetUp.yangpyeongFC;
        given(clubRepository.findByClubNameAndLocationAndDeleteFlagFalse(anyString(), anyString()))
                .willReturn(Optional.of(new Club(dto)));

        //when
        ClubInfoDto clubInfo = clubService.getClubInfo(dto.getClubName(), dto.getLocation());

        //then
        assertEquals(clubInfo.getClubName(), dto.getClubName());
        assertEquals(clubInfo.getLocation(), dto.getLocation());
    }

    @Test
    @DisplayName("클럽 이름으로 조회")
    void testFindByClubName() {
        //given
        String clubName = "양평FC";
        Member leader = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        List<Club> list = Arrays.asList(new Club(ClubInfoSetUp.yangpyeongFC), new Club(ClubInfoSetUp.seoulYangpyeongFC));
        given(clubRepository.findByClubName(anyString())).willReturn(Optional.of(list));

        //when
        List<ClubInfoDto> result = clubService.findByClubName(clubName);

        //then
        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("클럽 지역으로 조회")
    void testFindByLocation() {
        //given
        String clubLocation = "경기도 양평";
        List<Club> list = Arrays.asList(new Club(ClubInfoSetUp.yangpyeongFC), new Club(ClubInfoSetUp.yangpyeongProFC));

        given(clubRepository.findByLocation(anyString())).willReturn(Optional.of(list));

        //when
        List<ClubInfoDto> result = clubService.findByLocation(clubLocation);

        //then
        assertEquals(result.size(), 2);
    }
}
