package com.chun.crud.entity;

import com.chun.commons.enums.SubmitState;
import com.chun.crud.entitys.Club;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "submit_match_tbl")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubmitMatch{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    private String message;

    @CreationTimestamp
    private LocalDateTime submitTime;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invitation_id")
    private InvitationMatch invitation;

    @Enumerated(value = EnumType.STRING)
    private SubmitState state;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @Builder
    public SubmitMatch(String message, InvitationMatch invitation, Club club) {
        this.message = message;
        this.invitation = invitation;
        this.state = SubmitState.WAIT;
        this.club = club;
    }

    public SubmitMatch confirm(){
        this.state = SubmitState.CONFIRM;
        return this;
    }

    public SubmitMatch reject(){
        this.state = SubmitState.REJECT;
        return this;
    }
}
