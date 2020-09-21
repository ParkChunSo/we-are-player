package com.chun.apps.club.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.chun.cores.aws.s3.S3Source;
import com.chun.modules.aws.s3.S3Uploader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.chun.cores.aws.s3.S3ConfigVO;

@Configuration
public class S3Config {

    @Bean
    public S3ConfigVO s3ConfigVO(){
        return S3Source.createS3Source();
    }

    @Bean
    public AmazonS3 amazonS3(){
        return AmazonS3ClientBuilder.standard()
                .withRegion(s3ConfigVO().getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(s3ConfigVO().getAccessKey(), s3ConfigVO().getSecretKey())))
                .build();
    }

    @Bean
    public S3Uploader s3Uploader(){
        return new S3Uploader(s3ConfigVO().getBucket(), amazonS3());
    }
}
