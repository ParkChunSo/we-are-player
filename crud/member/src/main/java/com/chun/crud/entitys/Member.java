package com.chun.crud.entitys;


import com.chun.commons.enums.DisclosureScopeState;
import com.chun.commons.enums.MemberRole;
import com.chun.commons.enums.PositionType;
import com.chun.crud.dtos.MemberUpdateDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Entity(name = "member_tbl")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    private String id;

    @NotNull
    private String password;

    @NotNull
    private String name;

    private String city;

    private String district;

    @CreationTimestamp
    private LocalDateTime createDate;

    private int likeCnt;

    private int rudeCnt;

    private String pictureUri;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<MemberRole> roleSet;

    @Enumerated(value = EnumType.STRING)
    private PositionType position;

    @Enumerated(value = EnumType.STRING)
    private DisclosureScopeState disclosureScopeState;

    @Builder
    public Member(String id, String password, String name, String city, String district, String pictureUri, PositionType position, Set<MemberRole> roleSet) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.city = city;
        this.district = district;
        this.pictureUri = pictureUri;
        this.position = position;
        this.roleSet = roleSet;
        this.likeCnt = 0;
        this.rudeCnt = 0;
        this.disclosureScopeState = DisclosureScopeState.PUBLIC;
    }

    public Member updateInfo(MemberUpdateDto dto) {
        this.city = dto.getCity();
        this.district = dto.getDistrict();
        this.likeCnt = dto.getLikeCnt();
        this.rudeCnt = dto.getRudeCnt();
        this.pictureUri = dto.getPictureUri();
        this.position = dto.getPosition();
        this.disclosureScopeState = dto.getDisclosureScopeState();

        return this;
    }

    public Member updatePassword(String password){
        this.password = password;
        return this;
    }
}