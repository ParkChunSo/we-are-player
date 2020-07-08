package com.wap.chun.profile.club.service;

import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.profile.club.converter.ClubBasicInfoDtoConverter;
import com.wap.chun.profile.club.dtos.ClubBasicInfoDto;
import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.error.ClubAlreadyExistException;
import com.wap.chun.error.ClubCannotFindException;
import com.wap.chun.error.MemberCannotFindException;
import com.wap.chun.profile.club.dtos.ClubMemberDto;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO("AWS S3 추가하며 로고 저장하는 로직 구현")

/**
 * Club querydsl이 필요할까?
 * 클럽 팀원 제공하기(용병, 팀원)
 *  - 제공할 범위는?
 *  - QueryDsl을 사용하기
 * 기존 용병 및 지난 용병 보여주기
 *
 * 경기 기록 및 경기 일정은 Match에서 보여주자
 *
 * 팀원 용병 신청 기능도 여기서 하자
 * 팀원 CRUD(주의는 권한)
 *  - 일반 팀원은 읽기만.
 *  - 리더는 모두
 * 신청 목록 불러오기
 *
 */
@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final ClubMemberRepository clubMemberRepository;
    private ClubBasicInfoDtoConverter converter;

    private Member findMemberById(String memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(MemberCannotFindException::new);
    }

    private Club findClubByClubNameAndLocationAndLeaderId(String clubName, String location, String leaderId){
        return clubRepository.findByClubNameAndLocationAndLeader(clubName, location, leaderId)
                .orElseThrow(ClubCannotFindException::new);
    }

    public void createClub(ClubBasicInfoDto dto){
        if(clubRepository.isExisted(dto.getClubName(), dto.getLocation())) {
            throw new ClubAlreadyExistException();
        }

        converter = new ClubBasicInfoDtoConverter(findMemberById(dto.getLeaderId()));
        clubRepository.save(converter.convertToEntity(dto));
    }

    public ClubBasicInfoDto getClubBasicInfo(String clubName, String location, String leaderId){
        Club club = findClubByClubNameAndLocationAndLeaderId(clubName, location, leaderId);
        return converter.convertToDto(club);
    }

    public void updateClubLeader(String clubName, String location, String preLeaderId, String newLeaderId){
        Club club = findClubByClubNameAndLocationAndLeaderId(clubName, location, preLeaderId);
        club.setLeader(findMemberById(newLeaderId));
        clubRepository.save(club);
    }

    public void updateClubLogoUri(String clubName, String location, String leaderId, String logoUri){
        Club club = findClubByClubNameAndLocationAndLeaderId(clubName, location, leaderId);
        club.setLogoUri(logoUri);
        clubRepository.save(club);
    }

    public void updateLikeAndRudeCnt(String clubName, String location, String leaderId, Integer likeCnt, Integer rudeCnt){
        Club club = findClubByClubNameAndLocationAndLeaderId(clubName, location, leaderId);
        club.setLikeCnt(likeCnt);
        club.setRudeCnt(rudeCnt);
        clubRepository.save(club);
    }

    public List<ClubMemberDto> getClubMembers(String clubName, String location, String leaderId){
        return null;
    }
}
