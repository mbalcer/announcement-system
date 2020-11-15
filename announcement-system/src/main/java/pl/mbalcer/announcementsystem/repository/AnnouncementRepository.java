package pl.mbalcer.announcementsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mbalcer.announcementsystem.model.Announcement;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Page<Announcement> findAll(Pageable pageable);

    Page<Announcement> findAllByCategoryName(String categoryName, Pageable pageable);

    Page<Announcement> findAllByPlaceCity(String placeCity, Pageable pageable);

    Page<Announcement> findAllByCategoryNameAndPlaceCity(String categoryName, String placeCity, Pageable pageable);

    List<Announcement> findAllByUserUsername(String username);
}
