package com.wap.api.domain.entitys;

import com.wap.api.domain.enums.SubmitState;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "submit_match_tbl")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubmitMatch extends Submit{
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "request_club_id")
    private Club requestClub;

    public SubmitMatch updateState(SubmitState state){
        super.setState(state);
        return this;
    }

    @Builder
    public SubmitMatch(String message, Invitation invitation, Club club, Club requestClub){
        super(message, invitation);
        this.club = club;
        this.requestClub = requestClub;
    }
}
