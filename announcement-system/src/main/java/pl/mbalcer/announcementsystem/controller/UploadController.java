package pl.mbalcer.announcementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.mbalcer.announcementsystem.model.Announcement;
import pl.mbalcer.announcementsystem.service.UploadService;

import java.io.File;

@CrossOrigin
@RestController
@RequestMapping("/api/upload")
public class UploadController {
    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        File copyFile = uploadService.copyFile(file);
        Announcement photoUrl = new Announcement(copyFile.getName());
        return ResponseEntity.ok(photoUrl);
    }
}
