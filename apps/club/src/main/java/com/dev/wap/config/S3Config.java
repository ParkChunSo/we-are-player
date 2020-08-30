package com.dev.wap.config;

import com.amazonaws.services.s3.AmazonS3;
import com.dev.wap.S3ClientFactory;
import com.dev.wap.S3Uploader;
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
