package com.wap.api.profile.member.integration;

import com.wap.api.common.IntegrationTest;
import com.wap.api.domain.entitys.Member;
import com.wap.api.domain.enums.DisclosureScopeState;
import com.wap.api.domain.enums.MemberType;
import com.wap.api.domain.enums.PositionType;
import com.wap.api.error.exception.MemberNotFoundException;
import com.wap.api.profile.member.dtos.*;
import com.wap.api.profile.member.repository.MemberRepository;
import com.wap.api.profile.setup.MemberSetUp;
import com.wap.api.security.util.JwtTokenProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static com.wap.api.profile.setup.MemberSetUp.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MemberIntegrationTest extends IntegrationTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private String adminToken;

    public MemberIntegrationTest(WebApplicationContext context) {
        super(context);
    }

    @BeforeEach
    public void setUp(){
        adminToken = jwtTokenProvider.createToken("park@gmail.com", MemberType.ADMIN.getRoles());
//        park.setPassword(passwordEncoder.encode(park.getPassword()));
//        yun.setPassword(passwordEncoder.encode(yun.getPassword()));
//        kim.setPassword(passwordEncoder.encode(kim.getPassword()));
//        jeoung.setPassword(passwordEncoder.encode(jeoung.getPassword()));

    }

    @Test
    void testSignUpClient() throws Exception {
        //given
        MemberSignUpDto dto = MemberSignUpDto.builder()
                .id("park@gmail.com")
                .password("1234")
                .name("박춘소")
                .city("경기도")
                .district("양평군")
                .pictureUri("/image/profile/park")
                .type(MemberType.CLIENT)
                .position(PositionType.FW)
                .disclosureScopeState(DisclosureScopeState.PUBLIC)
                .build();

        //when
        mvc.perform(post("/member")
                .content(om.writeValueAsBytes(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);
        assertEquals(member.getId(), dto.getId());
    }

    @Test
    @WithMockUser(username = "park@gmail.com", roles = {"ADMIN"})
    void testSignUpAdmin() throws Exception {
        //given
        MemberSignUpDto dto = MemberSignUpDto.builder()
                .id("yun@gmail.com")
                .password("12345")
                .name("김윤상")
                .city("경기도")
                .district("광주시")
                .pictureUri("/image/profile/yun")
                .type(MemberType.ADMIN)
                .position(PositionType.DF)
                .disclosureScopeState(DisclosureScopeState.PUBLIC)
                .build();
        //when
        mvc.perform(post("/member/admin")
                .header("Authorization", "Bearer " + adminToken)
                .content(om.writeValueAsBytes(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);
        assertEquals(member.getId(), dto.getId());
        assertTrue(member.getRoleSet().containsAll(MemberType.ADMIN.getRoles()));
    }

    @Test
    void testLogin() throws Exception {
        //given
        Member park = MemberSetUp.park;
        park.setPassword(passwordEncoder.encode(park.getPassword()));
        memberRepository.save(park);

        MemberLoginDto dto = MemberLoginDto.builder()
                .id("park@gmail.com")
                .password("1234")
                .build();

        //when
        mvc.perform(post("/member/login")
                .content(om.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testFindById() throws Exception {
        //given
        Member park = MemberSetUp.park;
        park.setPassword(passwordEncoder.encode(park.getPassword()));
        memberRepository.save(park);

        //when
        MvcResult mvcResult = mvc.perform(get("/member/{userId}", "park@gmail.com"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        MemberInfoDto dto = om.readValue(mvcResult.getResponse().getContentAsString(), MemberInfoDto.class);
        assertEquals(dto.getId(), park.getId());
    }

    @Test
    void testFindDetailsInfoById() throws Exception {
        //given
        Member park = MemberSetUp.park;
        park.setPassword(passwordEncoder.encode(park.getPassword()));
        memberRepository.save(park);

        //when
        MvcResult mvcResult = mvc.perform(get("/member/details/{userId}", "park@gmail.com")
                .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        MemberDetailsInfoDto dto = om.readValue(mvcResult.getResponse().getContentAsString(), MemberDetailsInfoDto.class);
        assertEquals(dto.getId(), park.getId());
    }

    @Test
    void testUpdateMemberInfo() throws Exception {
        //given
        Member park = MemberSetUp.park;
        park.setPassword(passwordEncoder.encode(park.getPassword()));
        memberRepository.save(park);
        MemberInfoUpdateDto dto = MemberInfoUpdateDto.builder()
                .id("park@gmail.com")
                .password("1234")
                .city("경기도")
                .district("광주시")
                .pictureUri("/image/profile/park")
                .position(PositionType.FW)
                .disclosureScopeState(DisclosureScopeState.PUBLIC)
                .build();

        //when
        mvc.perform(put("/member")
                .header("Authorization", "Bearer " + adminToken)
                .content(om.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);
        assertEquals(member.getDistrict(), dto.getDistrict());
    }

    @Test
    void testUpdatePassword() throws Exception {
        //given
        Member park = MemberSetUp.park;
        park.setPassword(passwordEncoder.encode(park.getPassword()));
        memberRepository.save(park);
        MemberPasswordUpdateDto dto = MemberPasswordUpdateDto.builder()
                .id(park.getId())
                .prePassword("1234")
                .nowPassword("12345")
                .build();

        //when
        mvc.perform(put("/member/password")
                .header("Authorization", "Bearer " + adminToken)
                .content(om.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);
        assertTrue(passwordEncoder.matches(dto.getNowPassword(), member.getPassword()));
    }

    @Test
    void testDeleteMember() throws Exception {
        //given
        Member park = MemberSetUp.park;
        park.setPassword(passwordEncoder.encode(park.getPassword()));
        memberRepository.save(park);
        MemberDeleteDto dto = MemberDeleteDto.builder()
                .id(park.getId())
                .password("1234")
                .build();
        //when
        mvc.perform(delete("/member")
                .header("Authorization", "Bearer " + adminToken)
                .content(om.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        assertTrue(memberRepository.findById(park.getId()).isEmpty());
    }

    @Test
    void testFindAllMember() throws Exception {
        //given
        memberRepository.saveAll(Arrays.asList(park, yun, kim));
        //when
        mvc.perform(get("/member/all")
                .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        assertEquals(memberRepository.findAll().size(), 3);

    }
}
