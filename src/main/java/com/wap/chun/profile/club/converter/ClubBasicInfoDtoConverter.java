package com.wap.chun.profile.club.converter;

import com.wap.chun.common.Converter;
import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Member;
import com.wap.chun.profile.club.dtos.ClubBasicInfoDto;

public class ClubBasicInfoDtoConverter extends Converter<ClubBasicInfoDto, Club> {
    public ClubBasicInfoDtoConverter(Member leader) {
        super(dto -> Club.builder()
                        .clubName(dto.getClubName())
                        .location(dto.getLocation())
                        .leader(leader)
                        .build(),
                entity -> ClubBasicInfoDto.builder()
                        .clubName(entity.getClubName())
                        .location(entity.getLocation())
                        .logoUri(entity.getLogoUri())
                        .leaderId(entity.getLeader().getId())
                        .leaderName(entity.getLeader().getName())
                        .build()
        );
    }
}
