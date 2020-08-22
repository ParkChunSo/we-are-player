package com.wap.api.match.controller;

import com.wap.api.common.IntegrationTest;
import com.wap.api.domain.builder.ClubMemberBuilder;
import com.wap.api.domain.entitys.Club;
import com.wap.api.domain.entitys.Match;
import com.wap.api.match.dtos.request.MatchSaveDto;
import com.wap.api.match.repository.MatchRepository;
import com.wap.api.profile.club.repository.ClubMemberRepository;
import com.wap.api.profile.club.repository.ClubRepository;
import com.wap.api.profile.member.repository.MemberRepository;
import com.wap.api.profile.setup.ClubSetUp;
import com.wap.api.profile.setup.MemberSetUp;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MatchControllerTest extends IntegrationTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    ClubMemberRepository clubMemberRepository;

    @Autowired
    MatchRepository matchRepository;

    public MatchControllerTest(WebApplicationContext context) {
        super(context);
    }

    @BeforeEach
    void setUp() {
        memberRepository.saveAll(Arrays.asList(MemberSetUp.park, MemberSetUp.kim, MemberSetUp.yun, MemberSetUp.jeoung));
        Club ypFC = clubRepository.save(ClubSetUp.yangPyeongFC);
        Club seoulFC = clubRepository.save(ClubSetUp.seoulFC);

        clubMemberRepository.saveAll(Arrays.asList(
                ClubMemberBuilder.buildLeader(MemberSetUp.park, ypFC),
                ClubMemberBuilder.buildMember(MemberSetUp.kim, ypFC),
                ClubMemberBuilder.buildLeader(MemberSetUp.yun, seoulFC),
                ClubMemberBuilder.buildMember(MemberSetUp.jeoung, seoulFC)
        ));
    }

    @Test
    void saveMatch() throws Exception {
        //given
        Club homeClub = ClubSetUp.yangPyeongFC;
        Club awayClub = ClubSetUp.seoulFC;

        MatchSaveDto dto = MatchSaveDto.builder()
                .date(LocalDateTime.now())
                .homeClubCity(homeClub.getCity())
                .homeClubDistrict(homeClub.getDistrict())
                .homeClubName(homeClub.getClubName())
                .awayClubCity(awayClub.getCity())
                .awayClubDistrict(awayClub.getDistrict())
                .awayClubName(awayClub.getClubName())
                .matchCity("경기도")
                .matchDistrict("양평군")
                .matchDetailsAddress("강상체육공원")
                .build();

        //when
        mvc.perform(post("/match")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        List<Match> matches = matchRepository.findByHomeClubOrAwayClub(homeClub, awayClub).orElseThrow();
        assertEquals(matches.size(), 1);
        assertEquals(matches.get(0).getHomeClub(), homeClub);
        assertEquals(matches.get(0).getAwayClub(), awayClub);
    }

    @Test
    void getMatchInfoByLocationInMonth() {
    }

    @Test
    void getMatchInfoByClubInfoAndDate() {
    }

    @Test
    void getMatchByClubInfo() {
    }

    @Test
    void getTwoClubMatchInfoByClubInfo() {
    }

    @Test
    void getTwoClubMatchDetailsInfoByClubInfo() {
    }

    @Test
    void updateInfo() {
    }

    @Test
    void updateInfoAfterMatch() {
    }
}