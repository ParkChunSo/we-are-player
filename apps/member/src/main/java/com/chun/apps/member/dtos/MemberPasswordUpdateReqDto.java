package com.chun.apps.member.dtos;

import com.chun.crud.member.dtos.MemberPasswordUpdateDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberPasswordUpdateReqDto {
    private String id;
    private String prePassword;
    private String nowPassword;
    public MemberPasswordUpdateDto toMemberPasswordUpdateDto(String encodedPw){
        return MemberPasswordUpdateDto.builder()
                .id(this.id)
                .newPassword(encodedPw)
                .build();
    }
}
