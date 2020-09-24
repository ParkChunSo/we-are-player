package com.chun.commons.dtos.invitation;

import com.chun.commons.dtos.club.ClubResDto;
import com.chun.commons.enums.SubmitState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class SubmitMatchResDto {
    private long id;
    private String message;
    private LocalDateTime submitTime;
    private ClubResDto club;
    private SubmitState state;
}
