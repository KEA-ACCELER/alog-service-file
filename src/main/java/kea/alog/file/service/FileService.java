package kea.alog.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kea.alog.file.domain.FileRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileService {
    
    @Autowired
    private FileRepository fileRepository;
}
