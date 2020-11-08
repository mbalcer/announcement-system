package pl.mbalcer.announcementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mbalcer.announcementsystem.model.Voivodeship;

@Repository
public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Long> {
}
