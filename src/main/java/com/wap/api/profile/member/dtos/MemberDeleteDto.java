package com.wap.api.profile.member.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDeleteDto {
    private String id;
    private String password;
}
