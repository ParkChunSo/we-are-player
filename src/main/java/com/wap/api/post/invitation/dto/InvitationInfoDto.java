package com.wap.api.post.invitation.dto;

import com.wap.api.domain.entitys.Invitation;
import com.wap.api.domain.enums.InvitationType;
import com.wap.api.profile.club.dtos.response.ClubInfoDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InvitationInfoDto {
    private long id;
    private InvitationType invitationType;

    private String wantedCity;
    private String wantedDistrict;

    private ClubInfoDto clubInfo;

    private String writerId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String message;

    public InvitationInfoDto(Invitation entity){
        this.id = entity.getId();
        this.invitationType = entity.getInvitationType();
        this.wantedCity = entity.getCity();
        this.wantedDistrict = entity.getDistrict();
        this.clubInfo = new ClubInfoDto(entity.getClub());
        this.writerId = entity.getWriter().getId();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.message = entity.getMessage();
    }
}
