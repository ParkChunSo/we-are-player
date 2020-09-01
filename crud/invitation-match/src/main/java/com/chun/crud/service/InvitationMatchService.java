package com.chun.crud.service;

import com.chun.commons.errors.exception.ClubNotFoundException;
import com.chun.commons.errors.exception.InvitationNotFoundException;
import com.chun.crud.dto.InvitationMatchSaveDto;
import com.chun.crud.dto.InvitationMatchUpdateDto;
import com.chun.crud.entity.InvitationMatch;
import com.chun.crud.entitys.Club;
import com.chun.crud.repository.ClubCrudRepository;
import com.chun.crud.repository.InvitationMatchCrudRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InvitationMatchService {
    private final ClubCrudRepository clubCrudRepository;
    private final InvitationMatchCrudRepository invitationMatchCrudRepository;


    public InvitationMatch find(Long id) {
        return invitationMatchCrudRepository.findById(id)
                .orElseThrow(InvitationNotFoundException::new);
    }

    public InvitationMatch save(InvitationMatchSaveDto dto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        return invitationMatchCrudRepository.save(dto.toEntity(club));
    }

    public InvitationMatch update(InvitationMatchUpdateDto dto) {
        InvitationMatch invitation = invitationMatchCrudRepository.findById(dto.getId())
                .orElseThrow(InvitationNotFoundException::new);

        return invitationMatchCrudRepository.save(invitation.updateInfo(dto.getCity(), dto.getDistrict(),dto.getDetailsAddress(), dto.getDate(), dto.getMessage()));
    }

    public void delete(Long id) {
        invitationMatchCrudRepository.deleteById(id);
    }
}
