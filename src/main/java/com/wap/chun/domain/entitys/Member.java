package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.domain.enums.DisclosureScopeState;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.domain.enums.PositionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "member_tbl")
@NoArgsConstructor
@Getter
public class Member {
    @Id
    private String id;
    @NotNull
    private String password;
    @NotNull
    private String name;
    private String location;
    @CreationTimestamp
    private LocalDateTime createDate;
    private Integer likeCnt;
    private Integer rudeCnt;
    private String pictureUri;
    @Enumerated(value = EnumType.STRING)
    private MemberRole role;
    @Enumerated(value = EnumType.STRING)
    private PositionType position;
    @Enumerated(value = EnumType.STRING)
    private DisclosureScopeState disclosureScopeState;

    @Builder
    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.likeCnt = 0;
        this.rudeCnt = 0;
        this.role = MemberRole.CLIENT;
        this.disclosureScopeState = DisclosureScopeState.PUBLIC;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLikeCnt(Integer likeCnt) {
        this.likeCnt = likeCnt;
    }

    public void setRudeCnt(Integer rudeCnt) {
        this.rudeCnt = rudeCnt;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public void setPosition(PositionType position) {
        this.position = position;
    }

    public void setDisclosureScopeState(DisclosureScopeState disclosureScopeState) {
        this.disclosureScopeState = disclosureScopeState;
    }
}
