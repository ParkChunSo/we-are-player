package com.wap.chun.post.submit.dtos;

import com.wap.chun.domain.enums.SubmitState;
import com.wap.chun.profile.member.dtos.MemberInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class SubmitMemberInfoDto {
    private Long id;

    private String message;

    private LocalDateTime submitTime;

    private MemberInfoDto member;

    private SubmitState state;
}
