package pl.mbalcer.announcementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mbalcer.announcementsystem.model.Announcement;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByCategoryName(String categoryName);

    List<Announcement> findAllByPlaceCity(String placeCity);

    List<Announcement> findAllByCategoryNameAndPlaceCity(String categoryName, String placeCity);
}
