package com.S3Bucket.S3BucketAPI;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;


@Service
public class FileService {
    private static final String BUCKET_NAME = "project-bucket-demo";
    private final AmazonS3 amazonS3;
    public FileService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }
    public List<String> searchFiles(String userName, String searchTerm) {

        List<String> files = new ArrayList<>();
        String prefix = userName + "/";
        ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request().withBucketName(BUCKET_NAME)
                .withPrefix(prefix);
        ListObjectsV2Result result;

        do {
            result = amazonS3.listObjectsV2(listObjectsRequest);
            for (S3ObjectSummary summary : result.getObjectSummaries()) {
                if (summary.getKey().contains(searchTerm)) {
                    files.add(summary.getKey());
                }
            }

            listObjectsRequest.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());
        if(files.isEmpty()){
            files.add("Files not found");
            System.out.println(files.size());
        }
        return files;  
    }
}

