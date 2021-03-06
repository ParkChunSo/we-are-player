package com.wap.api.profile.club.dtos.params;

import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubInfoParam {
    private String name;
    private String city;
    private String district;
}
