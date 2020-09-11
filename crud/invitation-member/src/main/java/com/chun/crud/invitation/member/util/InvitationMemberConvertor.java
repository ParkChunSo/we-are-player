package com.chun.crud.invitation.member.util;

import com.chun.commons.dtos.invitation.InvitationPeopleResDto;
import com.chun.commons.dtos.invitation.SubmitPeopleResDto;
import com.chun.crud.club.util.ClubConvertor;
import com.chun.crud.invitation.member.entity.InvitationMember;
import com.chun.crud.invitation.member.entity.SubmitMember;
import com.chun.crud.member.util.MemberConvertor;

import java.util.stream.Collectors;

public class InvitationMemberConvertor {
    public static InvitationPeopleResDto toInvitationMemberResDto(InvitationMember entity){
        return InvitationPeopleResDto.builder()
                .id(entity.getId())
                .clubInfo(ClubConvertor.toClubResDto(entity.getClub()))
                .invitationType(entity.getInvitationType())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .message(entity.getMessage())
                .dtos(entity.getSubmitMemberList().stream()
                        .map(InvitationMemberConvertor::toSubmitMemberResDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static SubmitPeopleResDto toSubmitMemberResDto(SubmitMember entity){
        return SubmitPeopleResDto.builder()
                .id(entity.getId())
                .member(MemberConvertor.toMemberResDto(entity.getMember()))
                .state(entity.getState())
                .submitTime(entity.getSubmitTime())
                .message(entity.getMessage())
                .build();
    }
}
