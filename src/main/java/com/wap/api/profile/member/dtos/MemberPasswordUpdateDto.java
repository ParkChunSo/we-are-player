package com.wap.api.profile.member.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberPasswordUpdateDto {
    private String id;
    private String prePassword;
    private String nowPassword;
}
