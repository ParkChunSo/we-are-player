package com.wap.chun.post.invitation.dto;

import com.wap.chun.domain.entitys.Invitation;
import com.wap.chun.domain.enums.InvitationType;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InvitationInfoDto {
    private Long id;
    private InvitationType category;

    private String wantedCity;
    private String wantedDistrict;

    private ClubInfoDto clubInfo;

    private String writerId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String message;

    public InvitationInfoDto(Invitation entity){
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.wantedCity = entity.getCity();
        this.wantedDistrict = entity.getDistrict();
        this.clubInfo = new ClubInfoDto(entity.getClub());
        this.writerId = entity.getWriter().getId();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.message = entity.getMessage();
    }
}
