package com.chun.modules.crud.invitation.match.service;

import com.chun.commons.errors.exception.ClubNotFoundException;
import com.chun.commons.errors.exception.InvitationNotFoundException;
import com.chun.modules.crud.invitation.match.dtos.InvitationMatchSaveDto;
import com.chun.modules.crud.invitation.match.dtos.InvitationMatchUpdateDto;
import com.chun.modules.crud.invitation.match.entity.InvitationMatch;
import com.chun.modules.crud.club.entitys.Club;
import com.chun.modules.crud.club.repository.ClubCrudRepository;
import com.chun.modules.crud.invitation.match.repository.InvitationMatchCrudRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InvitationMatchCrudService {
    private final ClubCrudRepository clubCrudRepository;
    private final InvitationMatchCrudRepository invitationMatchCrudRepository;


    public InvitationMatch find(Long id) {
        return invitationMatchCrudRepository.findById(id)
                .orElseThrow(InvitationNotFoundException::new);
    }

    public List<InvitationMatch> findAll(){
        return invitationMatchCrudRepository.findAllByDateAfter(LocalDateTime.now())
                .orElse(new ArrayList<>());
    }

    public List<InvitationMatch> findByLocation(String city, String district){
        return invitationMatchCrudRepository.findByCityAndDistrict(city, district)
                .orElse(new ArrayList<>());
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
