package pl.mbalcer.announcementsystem.service;

import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.model.Category;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> findAll();
}
