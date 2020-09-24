package com.wap.api.s3.apps;

import com.amazonaws.services.s3.AmazonS3;
import com.wap.api.s3.bases.S3ClientFactory;
import com.wap.api.s3.modules.S3Uploader;
import org.springframework.context.annotation.Bean;

public class S3App {

    @Bean
    public AmazonS3 amazonS3() {
        return S3ClientFactory.createS3Client();
    }

    @Bean
    public S3Uploader s3Uploader() {
        return new S3Uploader(S3ClientFactory.createS3Client());
    }
}
