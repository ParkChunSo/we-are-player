package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.domain.enums.SubmitState;
import com.wap.chun.domain.enums.SubmitType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "submit_tbl")
@Getter
public class Submit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String message;

    @CreationTimestamp
    private LocalDateTime submitTime;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member member;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private SubmitType type;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private SubmitState state;

    @Builder
    public Submit(Club club, Member member, SubmitType type) {
        this.club = club;
        this.member = member;
        this.type = type;
        this.state = SubmitState.WAIT;
    }
}
