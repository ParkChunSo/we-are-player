package com.wap.chun.domain.entitys;

import com.sun.istack.NotNull;
import com.wap.chun.domain.enums.DisclosureScopeState;
import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.domain.enums.PositionType;
import com.wap.chun.profile.member.dtos.MemberInfoUpdateDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "member_tbl")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    private String id;

    @NotNull
    @Setter
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

    @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<MemberRole> roleSet;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private PositionType position;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private DisclosureScopeState disclosureScopeState;

    @Builder
    public Member(String id, String password, String name, String location, String pictureUri, PositionType position, Set<MemberRole> roleSet) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.location = location;
        this.pictureUri = pictureUri;
        this.position = position;
        this.roleSet = roleSet;
        this.likeCnt = 0;
        this.rudeCnt = 0;
        this.disclosureScopeState = DisclosureScopeState.PUBLIC;
    }

    public Member updateInfo(MemberInfoUpdateDto dto){
        this.location = dto.getLocation();
        this.likeCnt = dto.getLikeCnt();
        this.rudeCnt = dto.getRudeCnt();
        this.pictureUri = dto.getPictureUri();
        this.position = dto.getPosition();
        this.disclosureScopeState = dto.getDisclosureScopeState();

        return this;
    }
}
