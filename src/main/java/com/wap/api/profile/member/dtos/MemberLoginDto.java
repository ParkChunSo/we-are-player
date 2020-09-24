package com.wap.api.profile.member.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberLoginDto {
    private String id;
    private String password;
}
