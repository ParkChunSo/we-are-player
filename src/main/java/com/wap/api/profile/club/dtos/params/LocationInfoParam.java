package com.wap.api.profile.club.dtos.params;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfoParam {
    private String city;
    private String district;
}
