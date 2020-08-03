package com.wap.chun.profile.member.service;

import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.domain.enums.MemberType;
import com.wap.chun.error.exception.AccessDeniedAuthenticationException;
import com.wap.chun.error.exception.MemberAlreadyExistException;
import com.wap.chun.error.exception.MemberNotFoundException;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.dtos.*;
import com.wap.chun.profile.member.repository.MemberRepository;
import com.wap.chun.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;
    private final ClubMemberRepository clubMemberRepository;

    public String login(MemberLoginDto dto) {
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberNotFoundException();
        }

        return jwtTokenProvider.createToken(member.getId(), member.getRoleSet());
    }

    public void signUp(MemberSignUpDto dto) {
        if(memberRepository.existsById(dto.getId()))
            throw new MemberAlreadyExistException();

        if(dto.getType().equals(MemberType.ADMIN))
            throw new AccessDeniedAuthenticationException();

        Member member = Member.builder()
                .id(dto.getId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .roleSet(dto.getType().getRoles())
                .location(dto.getLocation())
                .pictureUri(dto.getPictureUri())
                .position(dto.getPosition())
                .build();

        memberRepository.save(member);
    }

    public void signUp(String token, MemberSignUpDto dto) {
        if(memberRepository.existsById(dto.getId()))
            throw new MemberAlreadyExistException();

        MemberRole role = Enum.valueOf(MemberRole.class, dto.getType().name());
        if(!jwtTokenProvider.hasRole(token, role))
            throw new AccessDeniedAuthenticationException();

        Member member = Member.builder()
                .id(dto.getId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .roleSet(dto.getType().getRoles())
                .location(dto.getLocation())
                .pictureUri(dto.getPictureUri())
                .position(dto.getPosition())
                .build();

        memberRepository.save(member);
    }

    public MemberInfoDto getMemberInfo(String userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(MemberNotFoundException::new);

        return new MemberInfoDto(member);
    }

    public MemberDetailsInfoDto getMemberDetailsInfo(String memberId, String token) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);

        List<ClubMember> clubMembers = clubMemberRepository
                .findByMemberAndClubMemberType(member, ClubMemberType.LEADER)
                .orElse(Collections.emptyList());

        String requestId = jwtTokenProvider.getUsername(token);

        //리더와 본인만 가능
        if (isAdmin(token) || checkSelfOrLeader(memberId, requestId, clubMembers)) {
            return new MemberDetailsInfoDto(member,
                    clubMembers.stream().map(ClubMember::getClub).collect(Collectors.toList()));
        }

        throw new AccessDeniedAuthenticationException();
    }
    private boolean checkSelfOrLeader(String memberId, String requestId, List<ClubMember> clubMembers){
        return (memberId.equals(requestId) || isLeader(clubMembers, requestId));
    }

    private boolean isLeader(List<ClubMember> clubMembers, String requestId) {
        if (clubMembers.isEmpty())
            return false;

        return clubMembers.stream()
                .map(leader -> leader.getMember().getId())
                .anyMatch(s -> s.equals(requestId));
    }

    private boolean isAdmin(String token){
        return jwtTokenProvider.hasRole(token, MemberRole.ADMIN);
    }

    public void updateMemberInfo(MemberInfoUpdateDto dto) {
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberNotFoundException();
        }

        memberRepository.save(member.updateInfo(dto));
    }

    public void updateMemberPassword(MemberPasswordUpdateDto dto) {
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(dto.getPrePassword(), member.getPassword())) {
            throw new MemberNotFoundException();
        }
        member.setPassword(passwordEncoder.encode(dto.getNowPassword()));
        memberRepository.save(member);
    }

    public void deleteMember(MemberDeleteDto dto) {
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberNotFoundException();
        }

        memberRepository.delete(member);
    }


    public List<MemberInfoDto> getAllMember() {
        return memberRepository.findAll().stream()
                .map(MemberInfoDto::new)
                .collect(Collectors.toList());
    }
}