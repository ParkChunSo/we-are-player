package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.domain.enums.SubmitState;
import com.wap.chun.domain.enums.SubmitType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "submit_tbl")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Submit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private String message;

    @CreationTimestamp
    private LocalDateTime submitTime;

    @ManyToOne
    @JoinColumn(name = "invitation_id")
    private Invitation invitation;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member member;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private SubmitState state;

    @Builder
    public Submit(String message, Invitation invitation, Club club, Member member) {
        this.message = message;
        this.invitation = invitation;
        this.club = club;
        this.member = member;
        this.state = SubmitState.WAIT;
    }
}
