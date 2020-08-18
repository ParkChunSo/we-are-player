package com.wap.api.profile.domain.entitys;

import com.wap.api.profile.domain.enums.ClubMemberType;
import com.wap.api.profile.domain.enums.SubmitState;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "submit_member_tbl")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubmitMember extends Submit{
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "request_member_id")
    private Member requestMember;

    @Enumerated(value = EnumType.STRING)
    private ClubMemberType clubMemberType;

    public SubmitMember updateState(SubmitState state){
        super.setState(state);
        return this;
    }

    @Builder
    public SubmitMember(String message, Invitation invitation, Club club, Member requestMember, ClubMemberType clubMemberType){
        super(message, invitation);
        this.club = club;
        this.requestMember = requestMember;
        this.clubMemberType = clubMemberType;
    }
}
