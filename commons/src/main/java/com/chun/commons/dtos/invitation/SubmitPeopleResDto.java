package com.chun.commons.dtos.invitation;

import com.chun.commons.dtos.member.MemberResDto;
import com.chun.commons.enums.SubmitState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class SubmitPeopleResDto {
    private long id;
    private String message;
    private LocalDateTime submitTime;
    private MemberResDto member;
    private SubmitState state;
}
