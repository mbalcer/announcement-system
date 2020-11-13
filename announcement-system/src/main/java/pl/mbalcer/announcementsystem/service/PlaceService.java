package pl.mbalcer.announcementsystem.service;

import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.model.Place;
import pl.mbalcer.announcementsystem.model.Voivodeship;

import java.util.List;

@Service
public interface PlaceService {
    List<Place> findAll();

    List<Place> findAllByVoivodeship(String voivodeship);

    List<Voivodeship> findAllVoivodeship();
}
