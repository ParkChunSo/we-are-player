package com.chun.apps.member.config;

import com.amazonaws.services.s3.AmazonS3;
import com.chun.modules.aws.s3.S3Uploader;
import com.wap.bases.S3ClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Bean
    public AmazonS3 amazonS3(){
        return S3ClientFactory.createS3Client();
    }

    @Bean
    public S3Uploader s3Uploader(){
        return new S3Uploader(S3ClientFactory.getBucket(), amazonS3());
    }
}
