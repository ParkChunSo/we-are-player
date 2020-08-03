package com.wap.chun.post.submit.dtos;

import com.wap.chun.domain.enums.SubmitState;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import com.wap.chun.profile.member.dtos.MemberInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class SubmitMatchInfoDto {
    private Long id;

    private String message;

    private LocalDateTime submitTime;

    private ClubInfoDto club;

    private SubmitState state;
}
