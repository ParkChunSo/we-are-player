package com.wap.api.s3.bases;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.wap.api.s3.cores.S3Confg;
import com.wap.api.s3.cores.S3Source;

public class S3ClientFactory {

    public static AmazonS3 createS3Client() {
        S3Confg s3Confg = S3Source.createS3Source();
        return AmazonS3ClientBuilder.standard()
                .withRegion(s3Confg.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(s3Confg.getAccessKey(), s3Confg.getSecretKey())))
                .build();
    }
}
