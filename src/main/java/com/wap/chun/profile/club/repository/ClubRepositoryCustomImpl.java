package com.wap.chun.profile.club.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.wap.chun.domain.entitys.Club;

import java.util.Optional;

public class ClubRepositoryCustomImpl implements com.wap.chun.profile.club.repository.ClubRepositoryCustom {
    @Override
    public Optional<Club> findByClubNameAndLocationAndLeader(String clubName, String Location, String LeaderId) {
//        JPAQuery<Club> query =
        return Optional.empty();
    }
}
