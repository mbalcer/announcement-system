package pl.mbalcer.announcementsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.model.Announcement;
import pl.mbalcer.announcementsystem.payload.response.MessageResponse;
import pl.mbalcer.announcementsystem.repository.AnnouncementRepository;
import pl.mbalcer.announcementsystem.service.AnnouncementService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {
    private AnnouncementRepository announcementRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public List<Announcement> findAll() {
        log.info("Request to get all announcements");
        return announcementRepository.findAll();
    }

    @Override
    public List<Announcement> findAllByCategory(String category) {
        log.info("Request to get all announcements by category: " + category);
        return announcementRepository.findAllByCategoryName(category);
    }

    @Override
    public List<Announcement> findAllByCity(String city) {
        log.info("Request to get all announcements by city: " + city);
        return announcementRepository.findAllByPlaceCity(city);
    }

    @Override
    public List<Announcement> findAllByCategoryAndCity(String category, String city) {
        log.info("Request to get all announcements by category: " + category + ", and city: " + city);
        return announcementRepository.findAllByCategoryNameAndPlaceCity(category, city);
    }

    @Override
    public ResponseEntity<?> findOne(Long id) {
        log.info("Request to get announcement by id: " + id);
        Optional<Announcement> announcementOptional = announcementRepository.findById(id);
        if (announcementOptional.isPresent())
            return ResponseEntity.ok(announcementOptional.get());
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> create(Announcement announcement) {
        log.info("Request to create announcement: " + announcement);
        if(announcement.getId() != null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid id!"));
        Announcement saveAnnouncement = announcementRepository.save(announcement);
        return ResponseEntity
                .created(URI.create("/api/announcement/"+saveAnnouncement.getId()))
                .body(saveAnnouncement);
    }

    @Override
    public ResponseEntity<?> update(Announcement announcement, Long id) {
        log.info("Request to update announcement: " + announcement);
        if (announcement.getId() != id)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("Error: The IDs must be the same!"));
        Announcement saveAnnouncement = announcementRepository.save(announcement);
        return ResponseEntity.ok(saveAnnouncement);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        log.info("Request to delete announcement by id: " + id);
        announcementRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}