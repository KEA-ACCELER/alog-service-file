package kea.alog.file.web;

import java.io.IOException;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.Response;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import jakarta.annotation.PostConstruct;
import kea.alog.file.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${custom.bucket-name}")
    private String bucketName;
    private AmazonS3Client amazonS3Client;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3Client.putObject(bucketName, s3FileName, multipartFile.getInputStream(), objMeta);

        return ResponseEntity.status(HttpStatus.CREATED).body(amazonS3Client.getUrl(bucketName, s3FileName).toString());
    }


   
}
