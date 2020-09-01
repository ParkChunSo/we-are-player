package com.chun.crud.service;

import com.chun.commons.errors.exception.ClubNotFoundException;
import com.chun.commons.errors.exception.InvitationNotFoundException;
import com.chun.crud.dtos.InvitationMemberSaveDto;
import com.chun.crud.dtos.InvitationMemberUpdateDto;
import com.chun.crud.entity.InvitationMember;
import com.chun.crud.entitys.Club;
import com.chun.crud.repository.ClubCrudRepository;
import com.chun.crud.repository.InvitationMemberCrudRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InvitationMemberCrudService {
    private final ClubCrudRepository clubCrudRepository;
    private final InvitationMemberCrudRepository invitationMemberCrudRepository;


    public InvitationMember find(Long id) {
        return invitationMemberCrudRepository.findById(id)
                .orElseThrow(InvitationNotFoundException::new);
    }

    public InvitationMember save(InvitationMemberSaveDto dto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        return invitationMemberCrudRepository.save(dto.toEntity(club));
    }

    public InvitationMember update(InvitationMemberUpdateDto dto) {
        InvitationMember invitation = invitationMemberCrudRepository.findById(dto.getId())
                .orElseThrow(InvitationNotFoundException::new);

        return invitationMemberCrudRepository.save(invitation.updateInfo(dto.getEndDate(), dto.getMessage()));
    }

    public void delete(Long id) {
        invitationMemberCrudRepository.deleteById(id);
    }
}
