package com.chun.crud.invitation.member.service;

import com.chun.commons.enums.InvitationType;
import com.chun.commons.errors.exception.ClubNotFoundException;
import com.chun.commons.errors.exception.InvitationNotFoundException;
import com.chun.crud.invitation.member.dtos.InvitationPeopleSaveDto;
import com.chun.crud.invitation.member.dtos.InvitationPeopleUpdateDto;
import com.chun.crud.invitation.member.entity.InvitationMember;
import com.chun.crud.entitys.Club;
import com.chun.crud.repository.ClubCrudRepository;
import com.chun.crud.invitation.member.respository.InvitationMemberCrudRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InvitationMemberCrudService {
    private final ClubCrudRepository clubCrudRepository;
    private final InvitationMemberCrudRepository invitationMemberCrudRepository;


    public InvitationMember find(Long id) {
        return invitationMemberCrudRepository.findById(id)
                .orElseThrow(InvitationNotFoundException::new);
    }

    public List<InvitationMember> findAll(InvitationType type) {
        return invitationMemberCrudRepository.findAllByInvitationTypeAndEndDateAfter(type, LocalDateTime.now())
                .orElse(new ArrayList<>());
    }

    public List<InvitationMember> findByLocation(String city, String district){
        return invitationMemberCrudRepository.findByClub_CityAndClub_District(city, district)
                .orElse(new ArrayList<>());
    }

    public InvitationMember save(InvitationPeopleSaveDto dto) {
        Club club = clubCrudRepository.findByClubNameAndCityAndDistrict(dto.getClubName(), dto.getClubCity(), dto.getClubDistrict())
                .orElseThrow(ClubNotFoundException::new);

        return invitationMemberCrudRepository.save(dto.toEntity(club));
    }

    public InvitationMember update(InvitationPeopleUpdateDto dto) {
        InvitationMember invitation = invitationMemberCrudRepository.findById(dto.getId())
                .orElseThrow(InvitationNotFoundException::new);

        return invitationMemberCrudRepository.save(invitation.updateInfo(dto.getEndDate(), dto.getMessage()));
    }

    public void delete(Long id) {
        invitationMemberCrudRepository.deleteById(id);
    }
}
