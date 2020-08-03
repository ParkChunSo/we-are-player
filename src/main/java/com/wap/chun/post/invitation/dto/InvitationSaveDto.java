package com.wap.chun.post.invitation.dto;

import com.wap.chun.domain.enums.InvitationType;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
public class InvitationSaveDto {
    private InvitationType category;

    private String wantedCity;
    private String wantedDistrict;

    private String clubName;
    private String clubCity;
    private String clubDistrict;

    private String writerId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String message;
}
