package com.crossover.imageupload.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public final class S3Uploader {
    private static Logger logger = LoggerFactory.getLogger(S3Uploader.class);
    private static AmazonS3 s3client;

    private S3Uploader() {

    }

    public static AmazonS3 getClient() {
        if (s3client != null) {
            return s3client;
        }

        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
        builder.setRegion(Regions.AP_SOUTH_1.getName());

        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        clientConfig.setCacheResponseMetadata(false);
        builder.setClientConfiguration(new ClientConfiguration());

        AmazonS3 client = builder.build();

        s3client = client;

        return client;
    }

    public static String uploadImage(final String bucket, final String base64Image,
                                     final String fileName) {
        logger.debug("UploadImage start params: bucket:" + bucket + " fileName:" + fileName);

        byte[] data = Base64.decodeBase64(base64Image);
        AmazonS3  client = getClient();

        createBucketIfNotExists(bucket, client);

        String file = createFile(fileName, bucket, client, data);

        logger.debug("UploadImage end");
        return file;
    }

    private static void createBucketIfNotExists(final String bucketName, final AmazonS3 client) {
        boolean bucketExists = false;
        for (Bucket bucket : client.listBuckets()) {
            if (bucket.getName().equals(bucketName)) {
                bucketExists = true;
                break;
            }
        }

        if (!bucketExists) {
            client.createBucket(bucketName);
        }
    }

    private static String createFile(final String fileName, final String bucketName, final AmazonS3 client,
                                     final byte[] bytes) {
        logger.debug("createFile start");
        InputStream stream = new ByteArrayInputStream(bytes);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);
        metadata.setContentType("image/jpeg");

        PutObjectRequest putRequest = new PutObjectRequest(bucketName, fileName, stream, metadata);
        putRequest = putRequest.withCannedAcl(CannedAccessControlList.PublicRead);

        logger.debug("client.putObject called");
        client.putObject(putRequest);

        logger.debug("createFile end");
        return client.getUrl(bucketName, fileName).toString();
    }
}
