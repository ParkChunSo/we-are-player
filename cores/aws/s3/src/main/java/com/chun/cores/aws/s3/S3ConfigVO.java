package com.chun.cores.aws.s3;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class S3ConfigVO {
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucket;
}
