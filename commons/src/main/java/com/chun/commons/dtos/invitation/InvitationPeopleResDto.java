package com.chun.commons.dtos.invitation;

import com.chun.commons.dtos.club.ClubResDto;
import com.chun.commons.enums.InvitationType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class InvitationPeopleResDto {
    private long id;
    private InvitationType invitationType;

    private ClubResDto clubInfo;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String message;
    List<SubmitMemberResDto> dtos;
}
