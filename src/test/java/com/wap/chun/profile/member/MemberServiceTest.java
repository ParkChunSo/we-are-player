package com.wap.chun.profile.member;

import com.wap.chun.domain.ClubBuilder;
import com.wap.chun.domain.ClubMemberBuilder;
import com.wap.chun.domain.MemberInfoSetUp;
import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.error.exception.MemberAlreadyExistException;
import com.wap.chun.error.exception.MemberNotFoundException;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.member.dtos.MemberDetailsInfoDto;
import com.wap.chun.profile.member.dtos.MemberLoginDto;
import com.wap.chun.profile.member.dtos.MemberSignUpDto;
import com.wap.chun.profile.member.repository.MemberRepository;
import com.wap.chun.profile.member.service.MemberService;
import com.wap.chun.security.util.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @InjectMocks
    MemberService memberService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    MemberRepository memberRepository;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    ClubMemberRepository clubMemberRepository;

    @Test
    @DisplayName("로그인 성공")
    void test_Login_Success() {
        //given
        MemberSignUpDto dto = MemberInfoSetUp.park;
        Member member = MemberInfoSetUp.toAdminEntity(dto);
        given(memberRepository.findById(anyString())).willReturn(Optional.of(member));
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);
        given(jwtTokenProvider.createToken(member.getId(), member.getRoleSet())).willReturn("token");

        //when
        final String token = memberService.login(new MemberLoginDto(dto.getId(), dto.getPassword()));

        //then
        assertEquals(token, "token");
    }

    @Test
    @DisplayName("로그인 실패")
    void test_Login_Fail() {
        //given
        MemberSignUpDto dto = MemberInfoSetUp.park;
        given(memberRepository.findById(anyString())).willReturn(Optional.empty());

        //when
        assertThrows(MemberNotFoundException.class, () -> memberService.login(new MemberLoginDto(dto.getId(), dto.getPassword())));
    }

    @Test
    @DisplayName("회원가입 실패")
    void test_SignUp_Fail() {
        //given
        given(memberRepository.existsById(any())).willReturn(true);

        //when
        assertThrows(MemberAlreadyExistException.class, () -> memberService.signUp(MemberInfoSetUp.park, MemberRole.CLIENT));

    }

    @Test
    @DisplayName("멤버 상세정보 가져오기(본인)")
    void test_GetMemberDetailsInfo_Success_Authentication() {
        //given
        Member member = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        given(memberRepository.findById(anyString())).willReturn(Optional.of(member));
        given(clubMemberRepository.findByMemberAndClubMemberType(any(), any())).willReturn(Optional.empty());
        given(jwtTokenProvider.getUsername(anyString())).willReturn(member.getId());

        //when
        MemberDetailsInfoDto dto = memberService.getMemberDetailsInfo(member.getId(), "token");

        //then
        assertEquals(member.getId(), dto.getId());
        assertNull(dto.getClubInfoDtoList());
    }

    @Test
    @DisplayName("멤버 상세정보 가져오기(리더)")
    void test_GetMemberDetailsInfo_Success_Leader() {
        //given
        Member leader = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        Member member = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim);

        Club club = ClubBuilder.build(leader);
        List<ClubMember> clubMembers = List.of(ClubMemberBuilder.build(member, club));

        given(memberRepository.findById(anyString())).willReturn(Optional.of(member));
        given(clubMemberRepository.findByMemberAndClubMemberType(any(), any())).willReturn(Optional.of(clubMembers));
        given(jwtTokenProvider.getUsername(anyString())).willReturn(leader.getId());

        //when
        MemberDetailsInfoDto dto = memberService.getMemberDetailsInfo(member.getId(), "token");

        //then
        assertEquals(member.getId(), dto.getId());
        assertEquals(dto.getClubInfoDtoList().size(), 1);
    }

    @Test
    @DisplayName("멤버 상세정보 가져오기(관리자)")
    void test_GetMemberDetailsInfo_Success_Admin() {
        //given
        Member leader = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        Member member = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim);
        Member admin = MemberInfoSetUp.toAdminEntity(MemberInfoSetUp.yun);

        Club club = ClubBuilder.build(leader);
        List<ClubMember> clubMembers = List.of(ClubMemberBuilder.build(member, club));

        given(memberRepository.findById(anyString())).willReturn(Optional.of(member));
        given(clubMemberRepository.findByMemberAndClubMemberType(any(), any())).willReturn(Optional.of(clubMembers));
        given(jwtTokenProvider.getUsername(anyString())).willReturn(admin.getId());
        given(jwtTokenProvider.hasRole(anyString(), any())).willReturn(true);

        //when
        MemberDetailsInfoDto dto = memberService.getMemberDetailsInfo(member.getId(), "token");

        //then
        assertEquals(member.getId(), dto.getId());
        assertEquals(dto.getClubInfoDtoList().size(), 1);
    }
}
