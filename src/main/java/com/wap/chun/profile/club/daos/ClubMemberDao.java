package com.wap.chun.profile.club.daos;

import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.domain.enums.PositionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class ClubMemberDao {
    private String memberName;
    private String memberId;
    private String location;
    private String pictureUri;
    private ClubMemberType type;
    private PositionType position;
}
