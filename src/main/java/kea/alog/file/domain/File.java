package kea.alog.file.domain;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name = "file")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class File extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_pk")
    private Long filePk;

    @Column(name = "file_name", length=150)
    private String fileName;

    @Column(name = "file_path", length=500)
    private String filePath;

    @Column(name = "file_deleted")
    private boolean fileDeleted;

    @Builder
    public File(String fileName, String filePath, boolean fileDeleted){
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileDeleted = fileDeleted;
    }


    
}
