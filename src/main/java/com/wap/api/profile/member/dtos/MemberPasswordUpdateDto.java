package com.wap.api.profile.member.dtos;

import lombok.Getter;

@Getter
public class MemberPasswordUpdateDto {
    private String id;
    private String prePassword;
    private String nowPassword;
}
