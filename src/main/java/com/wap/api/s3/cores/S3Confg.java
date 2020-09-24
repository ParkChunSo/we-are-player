package com.wap.api.s3.cores;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
@Getter
@Builder
public class S3Confg {
    private String accessKey;

    private String secretKey;


    private String region;
}
