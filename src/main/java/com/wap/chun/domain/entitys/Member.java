package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.domain.enums.DisclosureScopeState;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.domain.enums.PositionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @Setter
    private String location;

    @CreationTimestamp
    private LocalDateTime createDate;

    @Setter
    private Integer likeCnt;

    @Setter
    private Integer rudeCnt;

    @Setter
    private String pictureUri;

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private PositionType position;

    @Setter
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
}
