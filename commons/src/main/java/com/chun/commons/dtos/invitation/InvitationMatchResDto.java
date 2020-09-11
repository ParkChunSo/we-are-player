package com.chun.commons.dtos.invitation;


import com.chun.commons.dtos.club.ClubResDto;
import com.chun.commons.enums.InvitationType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Builder
public class InvitationMatchResDto {
    private long id;

    private String city;
    private String districts;
    private String detailsAddress;
    private LocalDateTime date;

    private ClubResDto clubInfo;
    private String message;
    List<SubmitMatchResDto> dtos;
}
