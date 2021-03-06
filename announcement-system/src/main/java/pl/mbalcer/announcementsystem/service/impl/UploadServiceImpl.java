package pl.mbalcer.announcementsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.mbalcer.announcementsystem.service.UploadService;

import java.io.*;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Value("${app.photos.url}")
    private String photosPath;

    @Override
    public File copyFile(MultipartFile file) {
        InputStream input = null;
        String originalFilename = null;
        try {
            input = file.getInputStream();
            originalFilename = Optional.ofNullable(file.getOriginalFilename()).orElseThrow(FileNotFoundException::new);
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", e.getMessage());
        } catch (IOException e) {
            log.error("Error during get input stream from file: {}", e.getMessage());
        }

        File newFile = new File(photosPath + UUID.randomUUID() + originalFilename.substring(originalFilename.length() - 4));
        try(OutputStream outputStream = new FileOutputStream(newFile)){
            IOUtils.copy(input, outputStream);
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", e.getMessage());
        } catch (IOException e) {
            log.error("Error during copy file: {}", e.getMessage());
        }

        log.info("Copy file " + originalFilename + " to " + newFile.getAbsolutePath());

        return newFile;
    }
}
