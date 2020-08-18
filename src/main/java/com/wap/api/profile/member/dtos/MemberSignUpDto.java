package com.wap.api.profile.member.dtos;

import com.wap.api.profile.domain.enums.DisclosureScopeState;
import com.wap.api.profile.domain.enums.MemberType;
import com.wap.api.profile.domain.enums.PositionType;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberSignUpDto {
    private String id;
    private String password;
    private String name;
    private String city;
    private String district;
    private String pictureUri;
    private PositionType position;
    private MemberType type;
    private DisclosureScopeState disclosureScopeState;
}
