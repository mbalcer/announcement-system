package pl.mbalcer.announcementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mbalcer.announcementsystem.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
