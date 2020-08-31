package com.chun.crud;

import com.chun.commons.enums.InvitationType;
import com.chun.crud.entitys.Club;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "invitation_member_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvitationMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private InvitationType invitationType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private Club club;


    @CreationTimestamp
    private LocalDateTime createDate;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    private String message;

    @OneToMany(mappedBy = "invitation", fetch = FetchType.LAZY)
    private List<SubmitMember> submitMemberList = new ArrayList<>();

    @Builder
    public InvitationMember(InvitationType invitationType, Club club, LocalDateTime startDate, LocalDateTime endDate, String message) {
        this.invitationType = invitationType;
        this.club = club;
        this.startDate = startDate;
        this.endDate = endDate;
        this.message = message;
    }

    public InvitationMember updateInfo(LocalDateTime endDate, String message){
        this.endDate = endDate;
        this.message = message;

        return this;
    }
}
