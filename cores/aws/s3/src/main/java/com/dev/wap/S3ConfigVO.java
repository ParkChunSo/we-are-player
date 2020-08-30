package com.dev.wap;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class S3ConfigVO {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String region;
}
