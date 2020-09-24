package com.chun.cores.aws.s3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class S3Source {
    private static final String PROPERTIES_FILE_NAME = "aws-s3-%s.properties";

    public static S3ConfigVO createS3Source() {
        try {
            Properties properties = new Properties();
            InputStream input = S3Source.class.getClassLoader().getResourceAsStream(String.format(PROPERTIES_FILE_NAME, System.getenv("--spring.profiles.active")));
            if(input == null){
                input = new FileInputStream(new File("src/main/resources/aws-s3-dev.properties"));
            }
            properties.load(input);
            return S3ConfigVO.builder()
                    .accessKey(properties.getProperty("wap.aws.s3.cores.accessKey"))
                    .secretKey(properties.getProperty("wap.aws.s3.cores.secretKey"))
                    .region(properties.getProperty("wap.aws.s3.cores.region"))
                    .bucket(properties.getProperty("wap.aws.s3.cores.bucket"))
                    .build();
        } catch (IOException e) {
//            log.error("e => {}", e.toString());
            throw new RuntimeException(e);
        }
    }
}
