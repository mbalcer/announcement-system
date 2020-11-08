package pl.mbalcer.announcementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mbalcer.announcementsystem.model.Announcement;
import pl.mbalcer.announcementsystem.service.AnnouncementService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping
    public List<Announcement> getAllAnnouncements() {
        return announcementService.findAll();
    }

    @GetMapping("/category/{category}")
    public List<Announcement> getAllAnnouncementsByCategory(@PathVariable String category) {
        return announcementService.findAllByCategory(category);
    }

    @GetMapping("/city/{city}")
    public List<Announcement> getAllAnnouncementsByCity(@PathVariable String city) {
        return announcementService.findAllByCity(city);
    }

    @GetMapping("/category/{category}/city/{city}")
    public List<Announcement> getAllAnnouncementsByCategoryAndCity(@PathVariable String category, @PathVariable String city) {
        return announcementService.findAllByCategoryAndCity(category, city);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnnouncementById(@PathVariable Long id) {
        return announcementService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<?> createAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.create(announcement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnouncement(@RequestBody Announcement announcement, @PathVariable Long id) {
        return announcementService.update(announcement, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        return announcementService.delete(id);
    }

}
