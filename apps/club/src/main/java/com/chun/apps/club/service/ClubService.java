package com.chun.apps.club.service;

import com.chun.apps.club.dtos.params.ClubInfoParam;
import com.chun.apps.club.dtos.params.LocationInfoParam;
import com.chun.apps.club.dtos.request.ClubInfoUpdateReqDto;
import com.chun.apps.club.dtos.request.ClubSaveReqDto;
import com.chun.apps.club.dtos.response.ClubResDto;
import com.chun.crud.dtos.ClubUpdateDto;
import com.chun.crud.entitys.Club;
import com.chun.crud.service.ClubCrudService;
import com.chun.modules.aws.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final S3Uploader s3Uploader;
    private final ClubCrudService clubCrudService;

    private final static String DIR_NAME = "club";

    public void save(Optional<MultipartFile> image, ClubSaveReqDto dto) throws IOException {
        String logoUri;
        if (image.isPresent()) {
            logoUri = s3Uploader.upload(image.get(), DIR_NAME, dto.getCity() + dto.getDistrict() + dto.getClubName());
        }else {
            logoUri = null;
        }
        clubCrudService.save(dto.toClubSaveDto(logoUri));
    }

    public ClubResDto find(ClubInfoParam dto) {
        Club club = clubCrudService.find(dto.toClubInfoDto());
        return new ClubResDto(club);
    }

    public List<ClubResDto> findByClubName(String clubName) {
        List<Club> clubList = clubCrudService.findClubByName(clubName);

        return clubList.stream()
                .map(ClubResDto::new)
                .collect(Collectors.toList());
    }

    public List<ClubResDto> findByLocation(LocationInfoParam dto) {
        List<Club> clubList = clubCrudService.findByLocation(dto.toLocationDto());

        return clubList.stream()
                .map(ClubResDto::new)
                .collect(Collectors.toList());
    }

    public void updateLogo(MultipartFile image, ClubInfoParam dto) throws IOException {
        Club club = clubCrudService.find(dto.toClubInfoDto());

        String logoUri = s3Uploader.upload(image, DIR_NAME, dto.getCity() + dto.getDistrict() + dto.getName());
        ClubUpdateDto updateDto = ClubUpdateDto.builder()
                .city(dto.getCity())
                .district(dto.getDistrict())
                .clubName(dto.getName())
                .likeCnt(club.getLikeCnt())
                .rudeCnt(club.getRudeCnt())
                .point(club.getPoint())
                .logoUri(logoUri)
                .build();
        clubCrudService.update(updateDto);
    }

    public void updateLikeAndRudeCnt(ClubInfoUpdateReqDto dto) {
        Club club = clubCrudService.find(dto.toClubInfoDto());

        ClubUpdateDto updateDto = ClubUpdateDto.builder()
                .city(club.getCity())
                .district(club.getDistrict())
                .clubName(club.getClubName())
                .likeCnt(dto.getLikeCnt())
                .rudeCnt(dto.getRudeCnt())
                .point(club.getPoint())
                .logoUri(club.getLogoUri())
                .build();
        clubCrudService.update(updateDto);
    }

    public void delete(ClubInfoParam dto) {
        clubCrudService.delete(dto.toClubDeleteDto());
    }

}
