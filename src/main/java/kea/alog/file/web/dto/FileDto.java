package kea.alog.file.web.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class FileDto {

    @Getter
    @NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
    @ToString
    public static class UploadFile{
        private String purpose; //user, issue, release
        @Nullable
        private String fileName;

        @Builder
        public UploadFile(String purpose, String fileName){
            this.purpose = purpose;
            this.fileName = fileName;
        }
        
    }
    
}
