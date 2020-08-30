package com.dev.wap;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class S3Source {
    private static final String YAML_FILE_NAME = "aws-s3-%s.yml";

    public static S3ConfigVO createS3Source() {
        try {
            Properties properties = new Properties();
            InputStream input = S3Source.class.getClassLoader().getResourceAsStream(String.format(YAML_FILE_NAME, System.getenv("--spring.profiles.active")));
            properties.load(input);
            return S3ConfigVO.builder()
                    .accessKey(properties.getProperty("wap.api.s3.cores.accesKey"))
                    .secretKey(properties.getProperty("wap.api.s3.cores.secretKey"))
                    .region(properties.getProperty("wap.api.s3.cores.region"))
                    .bucket("")
                    .build();
        } catch (IOException e) {
//            log.error("e => {}", e.toString());
            throw new RuntimeException(e);
        }
    }
}
