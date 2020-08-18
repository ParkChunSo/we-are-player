package com.wap.api.profile.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.api.profile.domain.enums.InvitationType;
import com.wap.api.post.invitation.dto.InvitationSaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "invitation_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private InvitationType invitationType;

    private String city;

    private String district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id")
    private Member writer;

    @CreationTimestamp
    private LocalDateTime createDate;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    private String message;

    @Builder
    public Invitation(InvitationType invitationType, String city, String district, Club club, Member writer, LocalDateTime startDate, LocalDateTime endDate) {
        this.invitationType = invitationType;
        this.city = city;
        this.district = district;
        this.club = club;
        this.writer = writer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Invitation(InvitationSaveDto dto, Club club, Member writer){
        this.invitationType = dto.getCategory();
        this.city = dto.getWantedCity();
        this.district = dto.getWantedDistrict();
        this.club = club;
        this.writer = writer;
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.message = dto.getMessage();
    }

    public Invitation updateInfo(String city, String district, LocalDateTime endDate, String message){
        this.city = city;
        this.district = district;
        this.endDate = endDate;
        this.message = message;
        return this;
    }
}
