package com.wap.chun.profile.club;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.wap.chun.common.IntegrationTest;
import com.wap.chun.domain.builder.ClubMemberDtoBuilder;
import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.domain.enums.PositionType;
import com.wap.chun.domain.request.ClubInfoSetUp;
import com.wap.chun.domain.request.MemberInfoSetUp;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.repository.MemberRepository;
import com.wap.chun.security.util.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class ClubIntegrationTest {
    static final ObjectMapper om = new JsonMapper();

    @Autowired
    MockMvc mvc;

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
        mvc.perform(post("/club/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "park")
    @DisplayName("클럽 정보 조회 성공")
    void testGetClubInfo() throws Exception {
        //given
        Club club = clubRepository.saveAndFlush(new Club(ClubInfoSetUp.yangpyeongFC));
        Member park = memberRepository.saveAndFlush(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park));
        Member kim = memberRepository.saveAndFlush(MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim));
        ClubMember clubMemberPark = clubMemberRepository.saveAndFlush(
                ClubMember.builder()
                        .member(park)
                        .club(club)
                        .clubMemberType(ClubMemberType.LEADER)
                        .positionType(PositionType.FW)
                        .uniformNum(7)
                        .build());
        ClubMember clubMemberKim = clubMemberRepository.saveAndFlush(
                ClubMember.builder()
                        .member(kim)
                        .club(club)
                        .clubMemberType(ClubMemberType.MEMBER)
                        .positionType(PositionType.MF)
                        .uniformNum(11)
                        .build());

        //when
        MvcResult result = mvc.perform(get("/club/info/name/{clubName}/city/{city}/district/{district}", "양평FC", "경기도", "양평군"))
//                .header("Authorization", "Bearer " + clientToken)
//                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        ClubInfoDto dto = om.readValue(result.getResponse().getContentAsString(), ClubInfoDto.class);
        assertNotNull(dto.getClubId());
        assertEquals(dto.getClubName(), club.getClubName());
        assertEquals(dto.getCity(), club.getCity());
        assertEquals(dto.getDistrict(), club.getDistrict());
        assertEquals(dto.getMembers().size(), 2);
    }
}
