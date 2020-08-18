package com.wap.api.profile.member;

import com.wap.api.common.ServiceTest;
import com.wap.api.profile.domain.builder.ClubBuilder;
import com.wap.api.profile.domain.builder.ClubMemberBuilder;
import com.wap.api.profile.domain.entitys.Club;
import com.wap.api.profile.domain.entitys.ClubMember;
import com.wap.api.profile.domain.entitys.Member;
import com.wap.api.profile.domain.request.MemberInfoSetUp;
import com.wap.api.error.exception.AccessDeniedAuthenticationException;
import com.wap.api.error.exception.MemberAlreadyExistException;
import com.wap.api.error.exception.MemberNotFoundException;
import com.wap.api.profile.club.repository.ClubMemberRepository;
import com.wap.api.profile.member.dtos.MemberDetailsInfoDto;
import com.wap.api.profile.member.dtos.MemberLoginDto;
import com.wap.api.profile.member.dtos.MemberSignUpDto;
import com.wap.api.profile.member.repository.MemberRepository;
import com.wap.api.profile.member.service.MemberService;
import com.wap.api.security.util.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ServiceTest
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
    void testLoginSuccess() {
        //given
        MemberSignUpDto dto = MemberInfoSetUp.park;
        Member member = MemberInfoSetUp.toAdminEntity(dto);
        given(memberRepository.findById(anyString())).willReturn(Optional.of(member));
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);
        given(jwtTokenProvider.createToken(member.getId(), member.getRoleSet())).willReturn("token");

        //when
        final String token = memberService.login(
                MemberLoginDto.builder()
                        .id(dto.getId())
                        .password(dto.getPassword())
                        .build());

        //then
        assertEquals(token, "token");
    }

    @Test
    @DisplayName("로그인 실패")
    void testLoginFail() {
        //given
        MemberSignUpDto dto = MemberInfoSetUp.park;
        given(memberRepository.findById(anyString())).willReturn(Optional.empty());

        //when
        assertThrows(MemberNotFoundException.class,
                () -> memberService.login(MemberLoginDto.builder()
                        .id(dto.getId())
                        .password(dto.getPassword()).build()));
    }

    @Test
    @DisplayName("회원가입 실패(아이디 중복)")
    void testSignUpFailBecauseDuplication() {
        //given
        given(memberRepository.existsById(any())).willReturn(true);

        //then
        assertThrows(MemberAlreadyExistException.class, () -> memberService.signUp(MemberInfoSetUp.park));

    }

    @Test
    @DisplayName("회원가입 실패(권한)")
    void testSignUpFailBecauseAuthentication() {
        //given
        given(memberRepository.existsById(any())).willReturn(false);

        //then
        assertThrows(AccessDeniedAuthenticationException.class, () -> memberService.signUp(MemberInfoSetUp.park));
    }

    @Test
    @DisplayName("멤버 상세정보 가져오기(본인)")
    void testGetMemberDetailsInfoSuccessByAuthentication() {
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
    void testGetMemberDetailsInfoSuccessByLeader() {
        //given
        Member leader = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        Member member = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim);

        Club club = ClubBuilder.yangpyeongFC;
        List<ClubMember> clubMembers = List.of(ClubMemberBuilder.buildMember(member, club));

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
    void testGetMemberDetailsInfoSuccessByAdmin() {
        //given
        Member leader = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.park);
        Member member = MemberInfoSetUp.toClientEntity(MemberInfoSetUp.kim);
        Member admin = MemberInfoSetUp.toAdminEntity(MemberInfoSetUp.yun);

        Club club = ClubBuilder.yangpyeongFC;
        List<ClubMember> clubMembers = List.of(ClubMemberBuilder.buildMember(member, club));

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
