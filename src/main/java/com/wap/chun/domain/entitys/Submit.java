package com.wap.chun.domain.entitys;

import com.wap.chun.domain.enums.SubmitState;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "submit_tbl")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Submit {
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

    @Setter(value = AccessLevel.PROTECTED)
    @Enumerated(value = EnumType.STRING)
    private SubmitState state;

    protected Submit(String message, Invitation invitation){
        this.message = message;
        this.invitation = invitation;
    }
}
