package com.wap.api.profile.club.integration;

import com.wap.api.common.IntegrationTest;
import com.wap.api.domain.builder.ClubMemberDtoBuilder;
import com.wap.api.domain.entitys.Club;
import com.wap.api.domain.entitys.ClubMember;
import com.wap.api.domain.entitys.Member;
import com.wap.api.domain.enums.ClubMemberType;
import com.wap.api.domain.enums.MemberRole;
import com.wap.api.domain.enums.PositionType;
import com.wap.api.error.exception.ClubMemberNotFoundException;
import com.wap.api.error.exception.ClubNotFoundException;
import com.wap.api.profile.club.dtos.request.ClubInfoUpdateDto;
import com.wap.api.profile.club.dtos.request.ClubLeaderUpdateDto;
import com.wap.api.profile.club.dtos.request.ClubMemberSaveDto;
import com.wap.api.profile.club.dtos.response.ClubMemberDto;
import com.wap.api.profile.club.setup.ClubInfoSetUp;
import com.wap.api.profile.club.setup.MemberInfoSetUp;
import com.wap.api.profile.club.dtos.response.ClubInfoDto;
import com.wap.api.profile.club.repository.ClubMemberRepository;
import com.wap.api.profile.club.repository.ClubRepository;
import com.wap.api.profile.club.setup.ClubSetUp;
import com.wap.api.profile.club.setup.MemberSetUp;
import com.wap.api.profile.member.repository.MemberRepository;
import com.wap.api.security.util.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ClubIntegrationTest extends IntegrationTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    ClubMemberRepository clubMemberRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    List<Member> members;
    String adminToken;

    public ClubIntegrationTest(WebApplicationContext context) {
        super(context);
    }

    @BeforeEach
    void setUp() {
        adminToken = jwtTokenProvider.createToken(MemberInfoSetUp.park.getId(), Set.of(MemberRole.CLIENT, MemberRole.ADMIN));

        members = memberRepository.saveAll(Arrays.asList(
                MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park),
                MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim),
                MemberInfoSetUp.toClientEntity(MemberInfoSetUp.yun),
                MemberInfoSetUp.toClientEntity(MemberInfoSetUp.jeoung)
        ));
    }

    @Test
    @WithMockUser(username = "park")
    @DisplayName("회원가입 성공")
    public void testCreateClub() throws Exception {
        //given
        ClubInfoDto dto = ClubInfoSetUp.yangpyeongFC;
        dto.setMembers(Arrays.asList(
                ClubMemberDtoBuilder.build(MemberInfoSetUp.park, ClubMemberType.LEADER),
                ClubMemberDtoBuilder.build(MemberInfoSetUp.kim, ClubMemberType.MEMBER)
        ));

        //then
        mvc.perform(post("/club")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "park")
    @DisplayName("클럽 정보 조회 성공")
    void testFindByClubInfo() throws Exception {
        //given
        Club club = ClubSetUp.yangPyeongFC;
        clubRepository.save(club);

        //when
        MvcResult result = mvc.perform(get("/club/info")
                    .param("name", "양평FC")
                    .param("city", "경기도")
                    .param("district", "양평군"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        ClubInfoDto dto = om.readValue(result.getResponse().getContentAsString(), ClubInfoDto.class);
        assertEquals(dto.getClubName(), club.getClubName());
        assertEquals(dto.getCity(), club.getCity());
        assertEquals(dto.getDistrict(), club.getDistrict());
    }

    @Test
    @WithMockUser(username = "park")
    void testFindByClubName() throws Exception {
        //given
        clubRepository.saveAll(Arrays.asList(ClubSetUp.yangPyeongFC, ClubSetUp.seoulYangpyeongFC, ClubSetUp.gangjuFC));

        //when
        MvcResult result = mvc.perform(get("/club/info/{name}", "양평FC"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        //then
        List<ClubInfoDto> dto = om.readValue(result.getResponse().getContentAsString()
                , om.getTypeFactory().constructCollectionType(List.class, ClubInfoDto.class));
        assertEquals(dto.size(), 2);
    }

    @Test
    @WithMockUser(username = "park")
    void testFindByLocation() throws Exception {
        //given
        clubRepository.saveAll(Arrays.asList(ClubSetUp.yangPyeongFC, ClubSetUp.yangpyeongProFC, ClubSetUp.seoulYangpyeongFC, ClubSetUp.gangjuFC));

        //when
        MvcResult result = mvc.perform(get("/club/info/location")
                .param("city", "경기도")
                .param("district", "양평군"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        //then
        ClubInfoDto[] dto = om.readValue(result.getResponse().getContentAsString(), ClubInfoDto[].class);

        assertEquals(dto.length, 2);
        for (ClubInfoDto o : dto) {
            assertEquals(o.getCity(), "경기도");
            assertEquals(o.getDistrict(), "양평군");
        }
    }

    @Test
    @WithMockUser(username = "park")
    void testFindMembersByClubInfo() throws Exception {
        //given
        Club club = clubRepository.save(ClubSetUp.yangPyeongFC);
        Member park = memberRepository.findById(MemberSetUp.park.getId()).get();
        Member kim = memberRepository.findById(MemberSetUp.kim.getId()).get();
        Member yun = memberRepository.findById(MemberSetUp.yun.getId()).get();
        clubMemberRepository.save(ClubMember.builder()
                .club(club)
                .clubMemberType(ClubMemberType.LEADER)
                .member(park)
                .positionType(PositionType.FW)
                .uniformNum(7)
                .build());
        clubMemberRepository.save(ClubMember.builder()
                .club(club)
                .clubMemberType(ClubMemberType.MEMBER)
                .member(kim)
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build());
        clubMemberRepository.save(ClubMember.builder()
                .club(club)
                .clubMemberType(ClubMemberType.MERCENARY)
                .member(yun)
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build());

        //when
        MvcResult result = mvc.perform(get("/club/info/members")
                .param("name", "양평FC")
                .param("city", "경기도")
                .param("district", "양평군"))

                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        //then
        ClubMemberDto[] dto = om.readValue(result.getResponse().getContentAsString(), ClubMemberDto[].class);
        assertEquals(dto.length, 1);
    }

    @Test
    @WithMockUser(username = "park")
    void testFindMercenariesByClubInfo() throws Exception {
        //given
        Club club = clubRepository.save(ClubSetUp.yangPyeongFC);
        Member park = memberRepository.findById(MemberSetUp.park.getId()).get();
        Member kim = memberRepository.findById(MemberSetUp.kim.getId()).get();
        Member yun = memberRepository.findById(MemberSetUp.yun.getId()).get();
        clubMemberRepository.save(ClubMember.builder()
                .club(club)
                .clubMemberType(ClubMemberType.LEADER)
                .member(park)
                .positionType(PositionType.FW)
                .uniformNum(7)
                .build());
        clubMemberRepository.save(ClubMember.builder()
                .club(club)
                .clubMemberType(ClubMemberType.MEMBER)
                .member(kim)
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build());
        clubMemberRepository.save(ClubMember.builder()
                .club(club)
                .clubMemberType(ClubMemberType.MERCENARY)
                .member(yun)
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build());

        //when
        MvcResult result = mvc.perform(get("/club/info/mercenaries")
                .param("name", "양평FC")
                .param("city", "경기도")
                .param("district", "양평군"))

                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        //then
        ClubMemberDto[] dto = om.readValue(result.getResponse().getContentAsString(), ClubMemberDto[].class);
        assertEquals(dto.length, 1);
    }

    @Test
    @WithMockUser(username = "park")
    void testSaveClubMembers() throws Exception {
        //given
        Club club = clubRepository.save(ClubSetUp.yangPyeongFC);
        Member park = memberRepository.findById(MemberSetUp.park.getId()).get();
        Member kim = memberRepository.findById(MemberSetUp.kim.getId()).get();
        Member yun = memberRepository.findById(MemberSetUp.yun.getId()).get();
        clubMemberRepository.save(ClubMember.builder()
                .club(club)
                .clubMemberType(ClubMemberType.LEADER)
                .member(park)
                .positionType(PositionType.FW)
                .uniformNum(7)
                .build());
        clubMemberRepository.save(ClubMember.builder()
                .club(club)
                .clubMemberType(ClubMemberType.MEMBER)
                .member(kim)
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build());

        ClubMemberSaveDto dto = ClubMemberSaveDto.builder()
                .clubName(club.getClubName())
                .clubCity(club.getCity())
                .clubDistrict(club.getDistrict())
                .clubMemberType(ClubMemberType.MERCENARY)
                .memberId(yun.getId())
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build();

        //when
        mvc.perform(post("/club/member")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+adminToken)
                .content(om.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        List<ClubMember> clubMembers = clubMemberRepository.findByClub_ClubNameAndClub_CityAndClub_District(club.getClubName(), club.getCity(), club.getDistrict())
                .orElseThrow(ClubMemberNotFoundException::new);
        assertEquals(clubMembers.size(), 3);
    }

    @Test
    @WithMockUser(username = "park")
    void testUpdateClub() throws Exception {
        //given
        Club club = clubRepository.save(ClubSetUp.yangPyeongFC);
        ClubInfoUpdateDto dto = ClubInfoUpdateDto.builder()
                .clubName("양평FC")
                .city("경기도")
                .district("양평군")
                .logoUri("/img/logo/yang2.png")
                .likeCnt(1)
                .rudeCnt(0)
                .build();

        //when
        mvc.perform(put("/club")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto)))

                .andExpect(status().isOk())
                .andDo(print());

        //then
        Club updatedClub = clubRepository.findById(club.getClubId()).orElseThrow(ClubNotFoundException::new);
        assertEquals(updatedClub.getLogoUri(), dto.getLogoUri());
        assertEquals(updatedClub.getLikeCnt(), dto.getLikeCnt());
    }

    @Test
    @WithMockUser(username = "park")
    void testUpdateLeader() throws Exception {
        //given
        Club club = clubRepository.save(ClubSetUp.yangPyeongFC);
        Member park = memberRepository.findById(MemberSetUp.park.getId()).get();
        Member kim = memberRepository.findById(MemberSetUp.kim.getId()).get();
        clubMemberRepository.save(ClubMember.builder()
                .club(club)
                .clubMemberType(ClubMemberType.LEADER)
                .member(park)
                .positionType(PositionType.FW)
                .uniformNum(7)
                .build());
        clubMemberRepository.save(ClubMember.builder()
                .club(club)
                .clubMemberType(ClubMemberType.MEMBER)
                .member(kim)
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build());
        ClubLeaderUpdateDto dto = ClubLeaderUpdateDto.builder()
                .city(club.getCity())
                .district(club.getDistrict())
                .clubName(club.getClubName())
                .preLeaderId(park.getId())
                .newLeaderId(kim.getId())
                .build();
        //when
        mvc.perform(put("/club/leader")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto)))

                .andExpect(status().isOk())
                .andDo(print());
        List<ClubMember> clubMembers = clubMemberRepository.findByClub_ClubNameAndClub_CityAndClub_DistrictAndClubMemberType(club.getClubName(), club.getCity(), club.getDistrict(), ClubMemberType.LEADER)
                .orElseThrow(ClubMemberNotFoundException::new);
        assertEquals(clubMembers.size(), 1);
        assertEquals(clubMembers.get(0).getMember(), kim);
    }

    @Test
    @WithMockUser(username = "park")
    void testDeleteClub() throws Exception {
        //given
        Club club = clubRepository.save(ClubSetUp.yangPyeongFC);

        //when
        mvc.perform(delete("/club")
                .param("name", "양평FC")
                .param("city", "경기도")
                .param("district", "양평군"))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        Club deletedClub = clubRepository.findById(club.getClubId()).orElseThrow(ClubNotFoundException::new);
        assertTrue(deletedClub.isDeleteFlag());
    }
}
