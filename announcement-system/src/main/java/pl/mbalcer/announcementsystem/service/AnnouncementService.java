package pl.mbalcer.announcementsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.model.Announcement;

import java.util.List;

@Service
public interface AnnouncementService {
    Page<Announcement> findAll(int page, int size);

    List<Announcement> findLatest(Long number);

    Page<Announcement> findAllByCategory(String category, int page, int size);

    Page<Announcement> findAllByCity(String city, int page, int size);

    Page<Announcement> findAllByCategoryAndCity(String category, String city, int page, int size);

    List<Announcement> findAllByUser(String username);

    ResponseEntity<?> findOne(Long id);

    ResponseEntity<?> create(Announcement announcement);

    ResponseEntity<?> update(Announcement announcement, Long id);

    ResponseEntity<Void> delete(Long id);
}
