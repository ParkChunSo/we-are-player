package com.chun.crud.invitation.member.entity;

import com.chun.commons.enums.SubmitState;
import com.chun.crud.member.entitys.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "submit_member_tbl")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubmitMember{
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
    private InvitationMember invitation;

    @Enumerated(value = EnumType.STRING)
    private SubmitState state;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public SubmitMember(String message, InvitationMember invitation, Member member) {
        this.message = message;
        this.invitation = invitation;
        this.state = SubmitState.WAIT;
        this.member = member;
    }

    public SubmitMember confirm(){
        this.state = SubmitState.CONFIRM;
        return this;
    }

    public SubmitMember reject(){
        this.state = SubmitState.REJECT;
        return this;
    }
}
