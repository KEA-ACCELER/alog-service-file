package kea.alog.file.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kea.alog.file.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
    
    @Autowired
    private FileService fileService;
}
