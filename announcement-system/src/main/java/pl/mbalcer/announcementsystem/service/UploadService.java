package pl.mbalcer.announcementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public interface UploadService {
    File copyFile(MultipartFile file);
}
