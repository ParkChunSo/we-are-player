package com.wap.api.post.invitation.dto;

import com.wap.api.domain.enums.InvitationType;
import lombok.Builder;
import lombok.Getter;

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
