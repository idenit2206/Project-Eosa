package com.eosa.web.util.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class FileService {

    @Value("${my.service.file.local.upload-path}") private String uploadPath;

    public String saveOne(MultipartFile file, Long usersIdx) {
        try{
            String uploadDirPath = uploadPath + "\\";
            String fileName = Long.toString(usersIdx) + file.getOriginalFilename();
            File uploadDir = new File(uploadDirPath);
            File uploadFile = new File(uploadDirPath+fileName);
            Path root = Paths.get(uploadDirPath);
            if(!uploadDir.exists()) {
                Files.createDirectory(root);
            }
            if(!uploadFile.exists()) {
                log.info("새로운 파일 {} 을 저장합니다.", fileName);
                Files.copy(file.getInputStream(), root.resolve(fileName));
            } else {
                log.info("{} 파일이 이미 서버에 존재합니다.", fileName);
            }

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("파일을 저장할 수 없습니다.", e);
        }
    }

}
