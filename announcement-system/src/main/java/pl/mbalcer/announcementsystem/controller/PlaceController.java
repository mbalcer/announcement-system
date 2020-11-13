package pl.mbalcer.announcementsystem.controller;

import org.springframework.web.bind.annotation.*;
import pl.mbalcer.announcementsystem.model.Place;
import pl.mbalcer.announcementsystem.model.Voivodeship;
import pl.mbalcer.announcementsystem.service.PlaceService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/place")
    public List<Place> getAllPlaces() {
        return this.placeService.findAll();
    }

    @GetMapping("/place/{voivodeship}")
    public List<Place> getAllPlaceByVoivodeship(@PathVariable String voivodeship) {
        return this.placeService.findAllByVoivodeship(voivodeship);
    }

    @GetMapping("/voivodeship")
    public List<Voivodeship> getAllVoivodeships() {
        return this.placeService.findAllVoivodeship();
    }
}
