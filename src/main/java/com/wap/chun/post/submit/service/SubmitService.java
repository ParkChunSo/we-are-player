package com.wap.chun.post.submit.service;

import com.wap.chun.domain.entitys.*;
import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.SubmitState;
import com.wap.chun.error.exception.ClubNotFoundException;
import com.wap.chun.error.exception.InvitationNotFoundException;
import com.wap.chun.error.exception.MemberNotFoundException;
import com.wap.chun.error.exception.SubmitNotFoundException;
import com.wap.chun.match.repository.MatchRepository;
import com.wap.chun.post.invitation.repository.InvitationRepository;
import com.wap.chun.post.submit.dtos.*;
import com.wap.chun.post.submit.repository.SubmitMatchRepository;
import com.wap.chun.post.submit.repository.SubmitMemberRepository;
import com.wap.chun.post.submit.repository.SubmitRepository;
import com.wap.chun.profile.club.repository.ClubMemberRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubmitService {
    private final SubmitRepository submitRepository;
    private final SubmitMemberRepository submitMemberRepository;
    private final SubmitMatchRepository submitMatchRepository;
    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final ClubMemberRepository clubMemberRepository;
    private final InvitationRepository invitationRepository;
    private final MatchRepository matchRepository;

    public SubmitMemberInfoDto getSubmitMemberById(Long id){
        SubmitMember submitMember = submitMemberRepository.findById(id)
                .orElseThrow(SubmitNotFoundException::new);
        return new SubmitMemberInfoDto(submitMember);
    }

    public SubmitMatchInfoDto getSubmitMatchById(Long id){
        SubmitMatch submitMember = submitMatchRepository.findById(id)
                .orElseThrow(SubmitNotFoundException::new);
        return new SubmitMatchInfoDto(submitMember);
    }

    public void saveSubmitForMemberOrMercenary(SubmitMemberSaveDto dto) {
        Invitation invitation = invitationRepository.findById(dto.getInvitationId())
                .orElseThrow(InvitationNotFoundException::new);

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        submitMemberRepository.save(
            SubmitMember.builder()
                .club(invitation.getClub())
                .clubMemberType(dto.getClubMemberType())
                .invitation(invitation)
                .message(dto.getMessage())
                .requestMember(member)
                .build());
    }

    public void saveSubmitForMatch(SubmitMatchSaveDto dto){
        Invitation invitation = invitationRepository.findById(dto.getInvitationId())
                .orElseThrow(InvitationNotFoundException::new);

        Club requestClub = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        submitMatchRepository.save(
                SubmitMatch.builder()
                .invitation(invitation)
                .club(invitation.getClub())
                .requestClub(requestClub)
                .message(dto.getMessage())
                .build());
    }

    // 멤버를 영입하는 것인지, 용병을 영입하는 것인지, 매칭의 대한 초대인지 확인
    // state를 어떻게 변경(수락인지, 거절인지)할 것인지 확인
    // 멤버, 용병일 경우 state 변경 후 clubmember 저장.
    // 매칭일 경우 state 변경 후 match 잡기, 해당 submit을 제외한 모든 submit 상태 거절로 변경.
    @Transactional
    public void updateSubmitForMatch(SubmitMatchUpdateDto dto) {
        SubmitMatch submit = submitMatchRepository.findById(dto.getId())
                .orElseThrow(SubmitNotFoundException::new);
        submitMatchRepository.save(submit.updateState(dto.getState()));

        if (dto.getState().equals(SubmitState.CONFIRM)) {
            matchRepository.saveAndFlush(
                    Match.builder()
                            .homeClub(submit.getClub())
                            .awayClub(submit.getRequestClub())
                            .city(dto.getCity())
                            .district(dto.getDistrict())
                            .detailsAddress(dto.getDetailsAddress())
                            .date(dto.getDate())
                            .build());
        }

    }

    @Transactional
    public void updateSubmitForMember(SubmitMemberUpdateDto dto) {
        SubmitMember submit = submitMemberRepository.findById(dto.getId())
                .orElseThrow(SubmitNotFoundException::new);
        submitMemberRepository.save(submit.updateState(dto.getState()));

        if (dto.getState().equals(SubmitState.CONFIRM)) {
            clubMemberRepository.saveAndFlush(
                    ClubMember.builder()
                            .member(submit.getRequestMember())
                            .club(submit.getClub())
                            .clubMemberType(dto.getClubMemberType())
                            .positionType(dto.getPositionType())
                            .uniformNum(dto.getUniformNum())
                            .build());
        }
    }

    public void deleteSubmit(Long id){
        submitRepository.deleteById(id);
    }
}
