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
	
    Regions clientRegion = Regions.DEFAULT_REGION;
    String bucketName = "*** Bucket name ***";
    
	public String save(MultipartFile file) throws IOException {
        try {

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .build();
            
            Long contentLength = Long.valueOf(file.getBytes().length);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/text");
            metadata.setContentLength(contentLength);
            
            s3Client.putObject(new PutObjectRequest(bucketName, file.getName(), file.getInputStream(), metadata));
            return bucketName + "/" + file.getName();
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
		return null;
		   
		   
	}
	
	 public static void main(String[] args) throws IOException {
	        
		    Regions clientRegion = Regions.DEFAULT_REGION;
	        String bucketName = "*** Bucket name ***";
	        String stringObjKeyName = "*** String object key name ***";
	        String fileObjKeyName = "*** File object key name ***";
	        String fileName = "*** Path to file to upload ***";

	        try {
	            //This code expects that you have AWS credentials set up per:
	            // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html
	            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
	                    .withRegion(clientRegion)
	                    .build();

	            // Upload a text string as a new object.
	            s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

	            // Upload a file as a new object with ContentType and title specified.
	            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
	            ObjectMetadata metadata = new ObjectMetadata();
	            metadata.setContentType("plain/text");
	            metadata.addUserMetadata("title", "someTitle");
	            request.setMetadata(metadata);
	            s3Client.putObject(request);
	        } catch (AmazonServiceException e) {
	            // The call was transmitted successfully, but Amazon S3 couldn't process 
	            // it, so it returned an error response.
	            e.printStackTrace();
	        } catch (SdkClientException e) {
	            // Amazon S3 couldn't be contacted for a response, or the client
	            // couldn't parse the response from Amazon S3.
	            e.printStackTrace();
	        }
	    }
	 
}
