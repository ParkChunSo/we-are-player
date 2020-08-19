package com.wap.api.domain.builder;

import com.wap.api.domain.entitys.Club;
import com.wap.api.domain.entitys.ClubMember;
import com.wap.api.domain.entitys.Member;
import com.wap.api.domain.enums.ClubMemberType;
import com.wap.api.domain.enums.PositionType;

public class ClubMemberBuilder {
    public static ClubMember buildMember(Member member, Club club){
        return ClubMember.builder()
                .club(club)
                .member(member)
                .clubMemberType(ClubMemberType.MEMBER)
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build();
    }

    public static ClubMember buildLeader(Member member, Club club){
        return ClubMember.builder()
                .club(club)
                .member(member)
                .clubMemberType(ClubMemberType.LEADER)
                .positionType(PositionType.FW)
                .uniformNum(11)
                .build();
    }

}
