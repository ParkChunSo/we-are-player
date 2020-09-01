package com.chun.crud.entity;

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

@Entity(name = "invitation_match_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvitationMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String city;

    private String district;

    private String detailsAddress;

    @NotNull
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private Club club;

    @CreationTimestamp
    private LocalDateTime createDate;

    private String message;

    @OneToMany(mappedBy = "invitation", fetch = FetchType.LAZY)
    private List<SubmitMatch> submitMatchList = new ArrayList<>();

    @Builder
    public InvitationMatch(String city, String district, String detailsAddress, LocalDateTime date, Club club, String message) {
        this.city = city;
        this.district = district;
        this.detailsAddress = detailsAddress;
        this.date = date;
        this.club = club;
        this.message = message;
    }

    public InvitationMatch updateInfo(String city, String district, String detailsAddress, LocalDateTime date, String message){
        this.city = city;
        this.district = district;
        this.detailsAddress = detailsAddress;
        this.date = date;
        this.message = message;

        return this;
    }

    public InvitationMatch addSubmit(SubmitMatch submitMatch){
        submitMatch.setInvitation(this);
        submitMatchList.add(submitMatch);

        return this;
    }
}
