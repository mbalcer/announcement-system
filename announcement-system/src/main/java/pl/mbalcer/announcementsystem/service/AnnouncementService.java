package pl.mbalcer.announcementsystem.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.model.Announcement;

import java.util.List;

@Service
public interface AnnouncementService {
    List<Announcement> findAll();

    List<Announcement> findAllByCategory(String category);

    List<Announcement> findAllByCity(String city);

    List<Announcement> findAllByCategoryAndCity(String category, String city);

    ResponseEntity<?> findOne(Long id);

    ResponseEntity<?> create(Announcement announcement);

    ResponseEntity<?> update(Announcement announcement, Long id);

    ResponseEntity<Void> delete(Long id);
}
