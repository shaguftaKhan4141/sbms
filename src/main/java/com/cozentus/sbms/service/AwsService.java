package com.cozentus.sbms.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class AwsService {
	
    Regions clientRegion = Regions.US_EAST_1;
    String bucketName = "sbms-document";
    
	public String save(MultipartFile file) throws IOException {
        try {

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .build();
            
            Long contentLength = Long.valueOf(file.getBytes().length);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/text");
            metadata.setContentLength(contentLength);
            
            s3Client.putObject(new PutObjectRequest(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata));
            return bucketName + "/" + file.getOriginalFilename();
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
		return null;
		   
		   
	}

	 
}
