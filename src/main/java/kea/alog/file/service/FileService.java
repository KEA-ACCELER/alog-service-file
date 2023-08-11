package kea.alog.file.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import kea.alog.file.domain.File;
import kea.alog.file.domain.FileRepository;
import kea.alog.file.web.dto.FileDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@RequiredArgsConstructor
@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private File file;

    @Value("${custom.bucket-name}")
    private String bucketName;

    @Autowired
    private AmazonS3Client amazonS3Client;

    private AmazonS3 amazonS3;

    @PostConstruct
    public void setS3() {
        this.amazonS3 = amazonS3Client;
    }

    @Transactional
    public List<File> saveFile(ArrayList<MultipartFile> multipartFiles ) throws IOException {
        
        List<File> files = new ArrayList<>();

        for (int i = 0; i < multipartFiles.size(); i++) {

            String fileName = "";
            MultipartFile multipartFile = multipartFiles.get(i);
            fileName = multipartFile.getOriginalFilename();
            if (fileName.length()>50){
                fileName = fileName.substring(0, 50);
            }
            String s3FileName = fileName + "-" + UUID.randomUUID().toString().substring(0, 10);

            ObjectMetadata objMeta = new ObjectMetadata();
            // 파일의 사이즈를 ContentLength로 S3에 알려주기 위해서 ObjectMetadata를 사용
            objMeta.setContentLength(multipartFile.getInputStream().available());

            // 파일 스트림을 열어서 파일 업로드
            amazonS3.putObject(new PutObjectRequest(bucketName, s3FileName, multipartFile.getInputStream(), objMeta)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            // 업로드된 파일 링크 주소를 리턴
            String filePath = amazonS3.getUrl(bucketName, s3FileName).toString();
            System.out.println("filePath : " + filePath);

            // db에 저장
            File file = File.builder().fileName(fileName).filePath(filePath).build();
            files.add(fileRepository.save(file));
        }
        return files;
    }


}