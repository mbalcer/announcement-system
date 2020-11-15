package pl.mbalcer.announcementsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.model.Announcement;
import pl.mbalcer.announcementsystem.model.User;
import pl.mbalcer.announcementsystem.payload.response.MessageResponse;
import pl.mbalcer.announcementsystem.repository.AnnouncementRepository;
import pl.mbalcer.announcementsystem.repository.UserRepository;
import pl.mbalcer.announcementsystem.service.AnnouncementService;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {
    private AnnouncementRepository announcementRepository;
    private UserRepository userRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, UserRepository userRepository) {
        this.announcementRepository = announcementRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<Announcement> findAll(int page, int size) {
        log.info("Request to get all announcements");
        Pageable pageable = PageRequest.of(page, size);
        return announcementRepository.findAll(pageable);
    }

    @Override
    public List<Announcement> findLatest(Long number) {
        log.info("Request to get "+ number +" latest announcements");
        return announcementRepository.findAll()
                .stream()
                .sorted(Comparator.nullsLast((a1, a2) -> a2.getDateTime().compareTo(a1.getDateTime())))
                .limit(number)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Announcement> findAllByCategory(String category, int page, int size) {
        log.info("Request to get all announcements by category: " + category);
        Pageable pageable = PageRequest.of(page, size);
        return announcementRepository.findAllByCategoryName(category, pageable);
    }

    @Override
    public Page<Announcement> findAllByCity(String city, int page, int size) {
        log.info("Request to get all announcements by city: " + city);
        Pageable pageable = PageRequest.of(page, size);
        return announcementRepository.findAllByPlaceCity(city, pageable);
    }

    @Override
    public Page<Announcement> findAllByCategoryAndCity(String category, String city, int page, int size) {
        log.info("Request to get all announcements by category: " + category + ", and city: " + city);
        Pageable pageable = PageRequest.of(page, size);
        return announcementRepository.findAllByCategoryNameAndPlaceCity(category, city, pageable);
    }

    @Override
    public List<Announcement> findAllByUser(String username) {
        log.info("Request to get all announcements by user: " + username);
        return this.announcementRepository.findAllByUserUsername(username);
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
        Optional<User> optionalUser = this.userRepository.findByUsername(announcement.getUser().getUsername());
        if (optionalUser.isEmpty())
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid user"));
        announcement.setUser(optionalUser.get());
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
