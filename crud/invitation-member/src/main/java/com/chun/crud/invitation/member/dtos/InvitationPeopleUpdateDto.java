package com.chun.crud.invitation.member.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class InvitationPeopleUpdateDto {
    private long id;
    private LocalDateTime endDate;
    private String message;
}
