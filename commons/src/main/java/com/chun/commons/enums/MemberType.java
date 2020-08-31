package com.chun.commons.enums;

import lombok.Getter;

import java.util.Set;

@Getter
public enum MemberType {
    CLIENT(Set.of(MemberRole.CLIENT)),
    ADMIN(Set.of(MemberRole.values()))
    ;


    private final Set<MemberRole> roles;

    MemberType(Set<MemberRole> roles) {
        this.roles = roles;
    }
}
