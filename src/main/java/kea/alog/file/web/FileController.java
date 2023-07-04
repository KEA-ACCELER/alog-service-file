package kea.alog.file.web;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.Response;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.StringUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import jakarta.annotation.PostConstruct;
import kea.alog.file.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${custom.bucket-name}")
    private String bucketName;

    @Autowired
    private AmazonS3Client amazonS3Client;

    private AmazonS3 amazonS3;

    @PostConstruct
    public void setS3() {
        this.amazonS3 = amazonS3Client;
    }

    @Operation(summary = "파일 업로드", description = "파일 업로드")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> upload(
            @Parameter(description = "multipart/form-data 형식의 이미지 리스트를 input으로 받습니다. 이때 key 값은 multipartFile 입니다.") @RequestParam("images") MultipartFile multipartFile)
            throws IOException {
        System.out.println("amazonS3Client : " + amazonS3Client.toString());
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        // 파일의 사이즈를 ContentLength로 S3에 알려주기 위해서 ObjectMetadata를 사용
        objMeta.setContentLength(multipartFile.getInputStream().available());

        // 파일 스트림을 열어서 파일 업로드
        amazonS3.putObject(new PutObjectRequest(bucketName, s3FileName, multipartFile.getInputStream(), objMeta).withCannedAcl(CannedAccessControlList.PublicRead));
        
        // 업로드된 파일 링크 주소를 리턴
        return ResponseEntity.status(HttpStatus.CREATED).body(amazonS3.getUrl(bucketName, s3FileName).toString());
    }

}
드