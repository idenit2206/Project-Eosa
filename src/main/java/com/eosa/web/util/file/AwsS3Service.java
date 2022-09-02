package com.eosa.web.util.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    @Value("${cloud.aws.s3.bucket}") private String bucket;
    @Value("${cloud.aws.s3.bucket.url}") private String bucketURL;
    private final AmazonS3 amazonS3;

    public String uploadSingleFile(MultipartFile file, String directoryName, Long companysIdx) {
        String fileName = directoryName + "_" +
                UUID.randomUUID().toString().substring(0,4) + "_" +
                String.valueOf(companysIdx);
        String fileURL = "";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try(InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket+"/"+directoryName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "S3에 registCerti 파일 업로드를 실패했습니다.");
        }
        fileURL = bucketURL+directoryName+"/"+fileName;
        return fileURL;
    }

    public List<String> uploadMultipleFile(List<MultipartFile> files, String directoryName, Long companysIdx) {
        List<String> fileURLList = new ArrayList<>();

        files.forEach(file -> {
            String fileName = directoryName + "_" +
                    UUID.randomUUID().toString().substring(0,4) + "_" +
                    String.valueOf(companysIdx);
            String fileURL = "";
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try(InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket+"/"+directoryName, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch(IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "S3에 다중파일 업로드에 실패했습니다.");
            }
            fileURL = bucketURL+directoryName+"/"+fileName;
            fileURLList.add(fileURL);
        });

        return fileURLList;
    }


}
