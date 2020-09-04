package com.chun.crud.member.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberPasswordUpdateDto {
    private String id;
    private String newPassword;
}
