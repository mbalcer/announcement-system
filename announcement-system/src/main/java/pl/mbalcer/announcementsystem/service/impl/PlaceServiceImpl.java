package pl.mbalcer.announcementsystem.service.impl;

import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.model.Place;
import pl.mbalcer.announcementsystem.model.Voivodeship;
import pl.mbalcer.announcementsystem.repository.PlaceRepository;
import pl.mbalcer.announcementsystem.repository.VoivodeshipRepository;
import pl.mbalcer.announcementsystem.service.PlaceService;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final VoivodeshipRepository voivodeshipRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository, VoivodeshipRepository voivodeshipRepository) {
        this.placeRepository = placeRepository;
        this.voivodeshipRepository = voivodeshipRepository;
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public List<Place> findAllByVoivodeship(String voivodeship) {
        return placeRepository.findAllByVoivodeshipName(voivodeship);
    }

    @Override
    public List<Voivodeship> findAllVoivodeship() {
        return voivodeshipRepository.findAll();
    }
}
