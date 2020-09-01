package com.chun.crud.service;

import com.chun.commons.enums.SubmitState;
import com.chun.commons.errors.exception.ClubNotFoundException;
import com.chun.commons.errors.exception.InvitationNotFoundException;
import com.chun.commons.errors.exception.SubmitNotFoundException;
import com.chun.crud.dto.SubmitMatchSaveDto;
import com.chun.crud.dto.SubmitMatchUpdateDto;
import com.chun.crud.entity.InvitationMatch;
import com.chun.crud.entity.Match;
import com.chun.crud.entity.SubmitMatch;
import com.chun.crud.entitys.Club;
import com.chun.crud.repository.ClubCrudRepository;
import com.chun.crud.repository.InvitationMatchCrudRepository;
import com.chun.crud.repository.MatchCrudRepository;
import com.chun.crud.repository.SubmitMatchCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class SubmitMatchCrudService {
    private final InvitationMatchCrudRepository invitationMatchCrudRepository;
    private final SubmitMatchCrudRepository submitMatchCrudRepository;
    private final ClubCrudRepository clubCrudRepository;
    private final MatchCrudRepository matchCrudRepository;

    public SubmitMatch save(SubmitMatchSaveDto dto) {
        InvitationMatch invitation = invitationMatchCrudRepository.findById(dto.getInvitationId())
                .orElseThrow(InvitationNotFoundException::new);

        Club requestClub = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        return submitMatchCrudRepository.save(
                SubmitMatch.builder()
                        .invitation(invitation)
                        .club(requestClub)
                        .message(dto.getMessage())
                        .build());
    }

    public SubmitMatch find(Long id) {
        return submitMatchCrudRepository.findById(id)
                .orElseThrow(SubmitNotFoundException::new);
    }

    @Transactional
    public void update(SubmitMatchUpdateDto dto) {
        SubmitMatch submit = submitMatchCrudRepository.findById(dto.getId())
                .orElseThrow(SubmitNotFoundException::new);

        InvitationMatch invitation = submit.getInvitation();

        if (dto.getState().equals(SubmitState.CONFIRM)) {
            submitMatchCrudRepository.save(submit.confirm());
            matchCrudRepository.save(
                    Match.builder()
                            .homeClub(invitation.getClub())
                            .awayClub(submit.getClub())
                            .city(invitation.getCity())
                            .district(invitation.getDistrict())
                            .detailsAddress(invitation.getDetailsAddress())
                            .date(invitation.getDate())
                            .build());
        }else{
            submitMatchCrudRepository.save(submit.reject());
        }
    }

    public void delete(Long id) {
        submitMatchCrudRepository.deleteById(id);
    }
}
