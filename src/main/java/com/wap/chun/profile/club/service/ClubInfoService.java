package com.wap.chun.profile.club.service;

import com.wap.chun.common.Converter;
import com.wap.chun.domain.dtos.ClubInfoDto;
import com.wap.chun.domain.entitys.Club;
import com.wap.chun.error.ClubAlreadyExistException;
import com.wap.chun.profile.club.repository.ClubRepository;
import com.wap.chun.profile.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ClubInfoService {
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private Converter<Club, ClubInfoDto> converter;

    public ClubInfoService(ClubRepository clubRepository, MemberRepository memberRepository, Converter<Club) {
        this.clubRepository = clubRepository;
        this.memberRepository = memberRepository;
        this.converter = new Converter<>(
                entity -> new ClubInfoDto(entity.getClubName(), entity.getLocation(),
                            entity.getLogoUri(), entity.getLeader().getId(),
                            entity.getLeader().getName()),
                dto -> new Club(dto.getClubName(), memberRepository.findById(dto.getLeaderId()).orElseThrow())
        );
    }

    public ClubInfoDto signUpClub(ClubInfoDto dto){
        if(clubRepository.isExisted(dto.getClubName(), dto.getLocation()))
            throw new ClubAlreadyExistException();

    }
}
