package com.mikro.gsmikro.bucket;

public enum BucketName {

    PROFILE_IMAGE("mikros-verify-upload");

    private final String bucketName;


    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
