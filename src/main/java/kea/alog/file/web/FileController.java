package kea.alog.file.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kea.alog.file.domain.File;
import kea.alog.file.service.FileService;
import kea.alog.file.web.dto.FileDto;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileController{

    @Autowired
    private FileService fileService;

    // @Operation(summary = "이미지 업로드", description = "이미지 업로드")
    // @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    // public ResponseEntity<List<File>> saveUserImg(
    //         @RequestPart("imgs") ArrayList<MultipartFile> multipartFiles,
    //         @RequestPart ArrayList<FileDto.UploadFile> uploadFiles) throws IOException {
    //     return ResponseEntity.status(HttpStatus.CREATED).body(fileService.saveFile(multipartFiles, uploadFiles));
    // }
    
    @Operation(summary = "이미지 업로드", description = "이미지 업로드")
    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<File>> saveImg(
            @RequestPart("imgs") ArrayList<MultipartFile> multipartFiles) throws IOException {
            log.info("업로드할 파일의 크기 : {}", multipartFiles.size());
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.saveFile(multipartFiles));
    }

}

