package com.chun.crud.dtos;

import com.chun.crud.entity.InvitationMember;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InvitationMemberUpdateDto {
    private long id;
    private LocalDateTime endDate;
    private String message;
}
