package com.petmily.backend.upload.controller;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        try{
            String folderName = "petmily";
            String originalFileName = file.getOriginalFilename(); // 확장자 포함 기존 파일 이름
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //파일 확장자 분리

            String fileName= UUID.randomUUID().toString() + extension;
            String fileKey = folderName + "/" + fileName;
            String fileUrl = "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/"+ fileKey;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket + "/"+ folderName, fileName, file.getInputStream(),metadata);
            System.out.println("파일 업로드 완료");
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e){
            e.printStackTrace();;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
