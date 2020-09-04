package com.chun.apps.club.dtos.params;

import com.chun.crud.dtos.LocationDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfoParam {
    private String city;
    private String district;

    public LocationDto toLocationDto(){
        return LocationDto.builder()
                .city(this.city)
                .district(this.district)
                .build();
    }
}
