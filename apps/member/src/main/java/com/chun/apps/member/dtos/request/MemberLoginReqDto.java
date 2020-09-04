package com.chun.apps.member.dtos.request;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberLoginReqDto {
    private String id;
    private String password;
}
