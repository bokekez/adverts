package codeVibe.task.controller;

import codeVibe.task.model.Adverts;
import codeVibe.task.service.AdvertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adverts")
public class AdvertsController {

    private final AdvertsService advertsService;

    @Autowired
    public AdvertsController(AdvertsService advertsService) {
        this.advertsService = advertsService;
    }

    @GetMapping("/")
    public List<Adverts> getAllAdverts() {
        return advertsService.getAllAdverts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adverts> getAdvertById(@PathVariable int id) {
        Adverts advert = advertsService.getAdvertById(id);
        if (advert != null) {
            return ResponseEntity.ok(advert);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
