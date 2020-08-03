package com.wap.chun.post.submit.service;

import com.wap.chun.domain.entitys.Invitation;
import com.wap.chun.domain.entitys.Submit;
import com.wap.chun.error.exception.InvitationNotFoundException;
import com.wap.chun.post.invitation.repository.InvitationRepository;
import com.wap.chun.post.submit.dtos.SubmitMemberSaveDto;
import com.wap.chun.post.submit.repository.SubmitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmitService {

    /**
     * 용병, 매칭할 클럽, 멤버의 대한 submit임
     * 매칭 등록, 용병 등록, 멤버 등록 각 상황의 대한 다른 dto
     * 클럽 지원자 확인
     *
     *
     *
     */
    private final SubmitRepository submitRepository;
    private final InvitationRepository invitationRepository;

    public void saveMemberSubmit(SubmitMemberSaveDto dto){
        Invitation invitation = invitationRepository.findById(dto.getInvitationId())
                .orElseThrow(InvitationNotFoundException::new);

        submitRepository.save(
                Submit.builder()
                .member(invitation.getWriter())
                .

        )
    }
}
