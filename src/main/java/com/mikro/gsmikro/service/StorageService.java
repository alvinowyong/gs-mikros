package com.mikro.gsmikro.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class StorageService {

    @Value("${bucket_name}")
    String BUCKET_NAME;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(File file, String fileName){
        s3Client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file));

        return "File uploaded: " + fileName;
    }
}
