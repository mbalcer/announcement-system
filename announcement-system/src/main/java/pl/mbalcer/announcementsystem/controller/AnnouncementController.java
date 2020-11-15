package pl.mbalcer.announcementsystem.controller;

import org.springframework.data.domain.Page;
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
    public Page<Announcement> getAllAnnouncements(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        return announcementService.findAll(page, size);
    }

    @GetMapping("/latest/{number}")
    public List<Announcement> getRandomAnnouncements(@PathVariable Long number) {
        return announcementService.findLatest(number);
    }

    @GetMapping("/category/{category}")
    public Page<Announcement> getAllAnnouncementsByCategory(@PathVariable String category,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return announcementService.findAllByCategory(category, page, size);
    }

    @GetMapping("/city/{city}")
    public Page<Announcement> getAllAnnouncementsByCity(@PathVariable String city,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return announcementService.findAllByCity(city, page, size);
    }

    @GetMapping("/category/{category}/city/{city}")
    public Page<Announcement> getAllAnnouncementsByCategoryAndCity(@PathVariable String category,
                                                                   @PathVariable String city,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        return announcementService.findAllByCategoryAndCity(category, city, page, size);
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
