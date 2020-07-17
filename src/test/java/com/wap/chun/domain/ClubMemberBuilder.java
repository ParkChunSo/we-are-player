package com.wap.chun.domain;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.ClubMember;
import com.wap.chun.domain.entitys.Member;

public class ClubMemberBuilder {
    public static ClubMember build(Member member, Club club){
        return ClubMember.builder()
                .club(club)
                .member(member)
                .build();
    }

}
