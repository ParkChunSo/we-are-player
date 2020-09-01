package com.chun.crud.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InvitationMatchUpdateDto {
    private long id;
    private String city;
    private String district;
    private String detailsAddress;
    private LocalDateTime date;
    private String message;
}
