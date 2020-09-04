package com.wap.bases;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.wap.cores.S3ConfigVO;
import com.wap.cores.S3Source;

public class S3ClientFactory {

    public static String getBucket(){
        return S3Source.createS3Source().getBucket();
    }

    public static AmazonS3 createS3Client() {
        S3ConfigVO s3ConfigVO = S3Source.createS3Source();
        return AmazonS3ClientBuilder.standard()
                .withRegion(s3ConfigVO.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(s3ConfigVO.getAccessKey(), s3ConfigVO.getSecretKey())))
                .build();
    }
}
