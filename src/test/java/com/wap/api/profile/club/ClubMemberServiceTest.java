package com.wap.api.profile.club;

import com.wap.api.common.ServiceTest;
import com.wap.api.profile.domain.builder.ClubMemberBuilder;
import com.wap.api.profile.domain.entitys.Club;
import com.wap.api.profile.domain.entitys.ClubMember;
import com.wap.api.profile.domain.entitys.Member;
import com.wap.api.profile.domain.enums.ClubMemberType;
import com.wap.api.profile.domain.enums.PositionType;
import com.wap.api.profile.domain.request.ClubInfoSetUp;
import com.wap.api.profile.domain.request.MemberInfoSetUp;
import com.wap.api.profile.club.dtos.ClubLeaderUpdateDto;
import com.wap.api.profile.club.dtos.ClubMemberDto;
import com.wap.api.profile.club.dtos.ClubMemberSaveDto;
import com.wap.api.profile.club.repository.ClubMemberRepository;
import com.wap.api.profile.club.repository.ClubRepository;
import com.wap.api.profile.club.service.ClubMemberService;
import com.wap.api.profile.member.repository.MemberRepository;
import com.wap.api.security.util.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ServiceTest
public class ClubMemberServiceTest {
    @InjectMocks
    ClubMemberService clubMemberService;
    @Mock
    ClubMemberRepository clubMemberRepository;
    @Mock
    ClubRepository clubRepository;
    @Mock
    MemberRepository memberRepository;
    @Mock
    JwtTokenProvider jwtTokenProvider;

    Club club = new Club(ClubInfoSetUp.yangpyeongFC);
    Member park = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
    Member yun = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.yun);
    Member jeoung = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.jeoung);
    Member kim = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim);
    List<ClubMember> clubMembers = Arrays.asList(
            ClubMemberBuilder.buildLeader(park, club),
            ClubMemberBuilder.buildMember(yun, club),
            ClubMemberBuilder.buildMember(jeoung, club),
            ClubMemberBuilder.buildMember(kim, club)
    );

    @Test
    @DisplayName("클럽멤버 조회하기 성공")
    void testGetClubMembersSuccess(){
        //given
        given(clubMemberRepository.findByClub_ClubNameAndClub_CityAndClub_DistrictAndClubMemberType(anyString(), anyString(),anyString(),any()))
                .willReturn(Optional.of(clubMembers));

        //when
        List<ClubMemberDto> dtos = clubMemberService.getClubMembers("양평FC", "경기도",  "양평군", ClubMemberType.MEMBER);

        //then
        assertEquals(dtos.size(), 3);
        for(ClubMemberDto dto : dtos){
            assertEquals(dto.getType(), ClubMemberType.MEMBER);
        }
    }
    @Test
    @DisplayName("클럽멤버 저장하기 성공")
    public void testSaveClubMemberSuccess() {
        //given
        club.setClubMembers(clubMembers);
        given(clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(anyString(), anyString(), anyString()))
                .willReturn(Optional.of(club));
        given(jwtTokenProvider.getUsername(anyString())).willReturn(park.getId());
        given(memberRepository.findById(anyString())).willReturn(Optional.of(Member.builder().id("gun@gmail.com").name("건우").build()));
        given(clubMemberRepository.save(any())).willReturn(null);

        ClubMemberSaveDto request = ClubMemberSaveDto.builder()
                .clubName("양평FC")
                .clubCity("경기도")
                .clubDistrict("양평군")
                .clubMemberType(ClubMemberType.MEMBER)
                .memberId("gun@gmail.com")
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build();

        //when
        clubMemberService.saveClubMember("token", request);
    }


    @Test
    @DisplayName("리더 변경 성공")
    public void testUpdateClubLeader() {
        //given
        ClubLeaderUpdateDto request = ClubLeaderUpdateDto.builder()
                .clubName("양평FC")
                .city("경기도")
                .district("양평군")
                .newLeaderId("park@gmail.com")
                .preLeaderId("yun@gmail.com")
                .build();
        given(clubMemberRepository.findByClub_ClubNameAndClub_CityAndClub_District(anyString(), anyString(), anyString())).willReturn(Optional.of(clubMembers));
        given(clubMemberRepository.saveAll(any())).willReturn(null);

        //when
        clubMemberService.updateClubLeader(request);
    }
}
