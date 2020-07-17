package com.wap.chun.profile.member.dtos;

import com.wap.chun.domain.enums.DisclosureScopeState;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.domain.enums.PositionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Builder
@AllArgsConstructor
public class MemberSignUpDto {
    private String id;
    private String password;
    private String name;
    private String location;
    private String pictureUri;
    private PositionType position;
    @Setter
    private DisclosureScopeState disclosureScopeState;
}
