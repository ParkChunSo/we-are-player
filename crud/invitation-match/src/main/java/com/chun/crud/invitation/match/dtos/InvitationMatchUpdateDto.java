package com.chun.crud.invitation.match.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class InvitationMatchUpdateDto {
    private long id;
    private String city;
    private String district;
    private String detailsAddress;
    private LocalDateTime date;
    private String message;
}
