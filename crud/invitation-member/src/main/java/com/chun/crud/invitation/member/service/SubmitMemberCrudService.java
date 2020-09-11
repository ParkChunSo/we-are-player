package com.chun.crud.invitation.member.service;

import com.chun.commons.enums.SubmitState;
import com.chun.commons.errors.exception.InvitationNotFoundException;
import com.chun.commons.errors.exception.MemberNotFoundException;
import com.chun.commons.errors.exception.SubmitNotFoundException;
import com.chun.crud.invitation.member.dtos.SubmitPeopleSaveDto;
import com.chun.crud.invitation.member.dtos.SubmitPeopleUpdateDto;
import com.chun.crud.invitation.member.entity.InvitationMember;
import com.chun.crud.invitation.member.entity.SubmitMember;
import com.chun.crud.entitys.ClubMember;
import com.chun.crud.invitation.member.respository.InvitationMemberCrudRepository;
import com.chun.crud.invitation.member.respository.SubmitMemberCrudRepository;
import com.chun.crud.member.entitys.Member;
import com.chun.crud.member.repository.MemberCrudRepository;
import com.chun.crud.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class SubmitMemberCrudService {
    private final InvitationMemberCrudRepository invitationMemberCrudRepository;
    private final SubmitMemberCrudRepository submitMemberCrudRepository;
    private final MemberCrudRepository memberCrudRepository;
    private final ClubMemberCrudRepository clubMemberCrudRepository;

    public SubmitMember save(SubmitPeopleSaveDto dto) {
        InvitationMember invitation = invitationMemberCrudRepository.findById(dto.getInvitationId())
                .orElseThrow(InvitationNotFoundException::new);

        Member member = memberCrudRepository.findById(dto.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        return submitMemberCrudRepository.save(
                SubmitMember.builder()
                        .invitation(invitation)
                        .message(dto.getMessage())
                        .member(member)
                        .build());
    }

    public SubmitMember find(Long id) {
        return submitMemberCrudRepository.findById(id)
                .orElseThrow(SubmitNotFoundException::new);
    }

    @Transactional
    public void update(SubmitPeopleUpdateDto dto) {
        SubmitMember submit = submitMemberCrudRepository.findById(dto.getId())
                .orElseThrow(SubmitNotFoundException::new);

        if (dto.getState().equals(SubmitState.CONFIRM)) {
            submitMemberCrudRepository.save(submit.confirm());
            clubMemberCrudRepository.save(
                    ClubMember.builder()
                            .member(submit.getMember())
                            .club(submit.getInvitation().getClub())
                            .clubMemberType(dto.getClubMemberType())
                            .positionType(dto.getPositionType())
                            .uniformNum(dto.getUniformNum())
                            .build());
        }else{
            submitMemberCrudRepository.save(submit.reject());
        }
    }

    public void delete(Long id) {
        submitMemberCrudRepository.deleteById(id);
    }
}
