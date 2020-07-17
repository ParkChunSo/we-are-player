package com.wap.chun.profile.member.service;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.error.exception.*;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.dtos.*;
import com.wap.chun.profile.member.repository.MemberRepository;
import com.wap.chun.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final ClubMemberRepository clubMemberRepository;

    public String login(MemberLoginDto dto) {
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberNotFoundException();
        }

        return jwtTokenProvider.createToken(member.getId(), member.getRoleSet());
    }

    public void signUp(MemberSignUpDto dto, MemberRole role) {
        if(memberRepository.existsById(dto.getId()))
            throw new MemberAlreadyExistException();

        Set<MemberRole> roles = new HashSet<>();
        roles.add(MemberRole.CLIENT);
        if (role.equals(MemberRole.ADMIN)) {
            roles.add(MemberRole.ADMIN);
        }

        Member member = Member.builder()
                .id(dto.getId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .roleSet(roles)
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

    public MemberDetailsInfoDto getMemberDetailsInfo(String userId, String token) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(MemberNotFoundException::new);

        List<ClubMember> clubMembers = clubMemberRepository.findByMemberAndClubMemberType(member, ClubMemberType.MEMBER)
                .orElse(Collections.emptyList());

        String requestId = jwtTokenProvider.getUsername(token);

        //리더와 본인만 가능
        if (userId.equals(requestId) //본인
                || (!clubMembers.isEmpty() && isLeader(clubMembers, requestId)) //리더
                || jwtTokenProvider.hasRole(token, MemberRole.ADMIN)) { //관리자

            return new MemberDetailsInfoDto(member, clubMembers.stream().map(ClubMember::getClub).collect(Collectors.toList()));
        }

        throw new AccessDenideAuthenticationException();
    }

    private boolean isLeader(List<ClubMember> list, String requestId) {
        if (list.isEmpty())
            return false;

        return list.stream()
                .map(clubMember -> clubMember.getClub().getLeader().getId())
                .anyMatch(s -> s.equals(requestId));
    }

    //TODO("삭제 고려, 해당 클래스에 있을 필요가 없지않을까?")
    public List<MemberInfoDto> findMembersByClub(String clubName, String clubLocation, ClubMemberType type) {
        Club club = clubRepository.findByClubNameAndLocation(clubName, clubLocation)
                .orElseThrow(ClubNotFoundException::new);
        List<ClubMember> clubMembers = clubMemberRepository.findByClubAndClubMemberType(club, type)
                .orElseThrow(ClubMemberNotFoundException::new);
        return clubMembers.stream()
                .map(clubMember -> new MemberInfoDto(clubMember.getMember()))
                .collect(Collectors.toList());
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