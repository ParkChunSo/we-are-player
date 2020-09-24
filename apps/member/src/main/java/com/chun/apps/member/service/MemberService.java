package com.chun.apps.member.service;

import com.chun.commons.dtos.member.MemberResDto;
import com.chun.apps.member.dtos.MemberPasswordUpdateReqDto;
import com.chun.apps.member.dtos.MemberUpdateReqDto;
import com.chun.apps.member.dtos.request.MemberLoginReqDto;
import com.chun.apps.member.dtos.request.MemberSaveReqDto;
import com.chun.commons.enums.MemberRole;
import com.chun.commons.enums.MemberType;
import com.chun.commons.errors.exception.AccessDeniedAuthenticationException;
import com.chun.commons.errors.exception.AuthorizationException;
import com.chun.commons.errors.exception.MemberNotFoundException;
import com.chun.modules.crud.member.dtos.MemberUpdateDto;
import com.chun.modules.crud.member.entitys.Member;
import com.chun.modules.crud.member.service.MemberCrudService;
import com.chun.modules.crud.member.util.MemberConvertor;
import com.chun.modules.aws.s3.S3Uploader;
import com.chun.modules.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final S3Uploader s3Uploader;

    private final MemberCrudService memberCrudService;

    private static final String DIR_NAME = "profile";

    public String login(MemberLoginReqDto dto) {
        Member member = memberCrudService.find(dto.getId());

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberNotFoundException();
        }

        return jwtTokenProvider.createToken(member.getId(), member.getRoleSet());
    }

    public void signUp(MemberSaveReqDto dto, MultipartFile image) throws IOException {
        if (dto.getType().equals(MemberType.ADMIN))
            throw new AccessDeniedAuthenticationException();

        String uploadedImg = s3Uploader.upload(image, DIR_NAME, dto.getId());
        String encodedPw = passwordEncoder.encode(dto.getPassword());
        memberCrudService.save(dto.toMemberSaveDto(uploadedImg, encodedPw));
    }

    public void signUp(String token, MemberSaveReqDto dto, MultipartFile image) throws IOException {
        MemberRole role = Enum.valueOf(MemberRole.class, dto.getType().name());
        if (!jwtTokenProvider.hasRole(jwtTokenProvider.resolveToken(token), role))
            throw new AccessDeniedAuthenticationException();

        String uploadedImg = s3Uploader.upload(image, DIR_NAME, dto.getId());
        String encodedPw = passwordEncoder.encode(dto.getPassword());
        memberCrudService.save(dto.toMemberSaveDto(uploadedImg, encodedPw));
    }

    public MemberResDto find(String userId) {
        Member member = memberCrudService.find(userId);

        return MemberConvertor.toMemberResDto(member);
    }

    public List<MemberResDto> findAll() {
        return memberCrudService.findAll().stream()
                .map(MemberConvertor::toMemberResDto)
                .collect(Collectors.toList());
    }

    //    //TODO("데이터 가져오는 쿼리문 문제")
//    public MemberDetailsInfoDto getMemberDetailsInfo(String memberId, String token) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(MemberNotFoundException::new);
//
//        List<ClubMember> clubMembers = clubMemberRepository
//                .findByMemberAndClubMemberType(member, ClubMemberType.LEADER)
//                .orElse(Collections.emptyList());
//
//        token = jwtTokenProvider.resolveToken(token);
//        String requestId = jwtTokenProvider.getUsername(token);
//
//        //리더와 본인만 가능
//        if (isAdmin(token) || checkSelfOrLeader(memberId, requestId, clubMembers)) {
//            return new MemberDetailsInfoDto(member,
//                    clubMembers.stream().map(ClubMember::getClub).collect(Collectors.toList()));
//        }
//
//        throw new AccessDeniedAuthenticationException();
//    }
//    private boolean checkSelfOrLeader(String memberId, String requestId, List<ClubMember> clubMembers) {
//        return (memberId.equals(requestId) || isLeader(clubMembers, requestId));
//    }
//
//    private boolean isLeader(List<ClubMember> clubMembers, String requestId) {
//        if (clubMembers.isEmpty())
//            return false;
//
//        return clubMembers.stream()
//                .map(leader -> leader.getMember().getId())
//                .anyMatch(s -> s.equals(requestId));
//    }
//
//    private boolean isAdmin(String token) {
//        return jwtTokenProvider.hasRole(token, MemberRole.ADMIN);

//    }

    public void update(String token, MemberUpdateReqDto dto) {
        Member member = memberCrudService.find(dto.getId());
        if (checkSelf(token, dto.getId()))
            throw new AuthorizationException();

        memberCrudService.update(dto.toMemberUpdateDto(member.getPictureUri()));
    }

    private boolean checkSelf(String token, String userId) {
        return userId.equals(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(token)));
    }

    public void updatePassword(String token, MemberPasswordUpdateReqDto dto) {
        if (checkSelf(token, dto.getId()))
            throw new AuthorizationException();

        memberCrudService.updatePassword(dto.getId(), passwordEncoder.encode(dto.getNowPassword()));
    }

    public void updateImage(String token, String userId, MultipartFile image) throws IOException {
        if (checkSelf(token, userId))
            throw new AuthorizationException();
        Member member = memberCrudService.find(userId);
        memberCrudService.update(MemberUpdateDto.builder()
                .id(member.getId())
                .city(member.getCity())
                .district(member.getDistrict())
                .pictureUri(s3Uploader.upload(image, DIR_NAME, userId))
                .position(member.getPosition())
                .likeCnt(member.getLikeCnt())
                .rudeCnt(member.getRudeCnt())
                .disclosureScopeState(member.getDisclosureScopeState())
                .build());
    }

    public void deleteMember(String token, String userId) {
        if (checkSelf(token, userId))
            throw new AuthorizationException();

        memberCrudService.delete(userId);
    }
}