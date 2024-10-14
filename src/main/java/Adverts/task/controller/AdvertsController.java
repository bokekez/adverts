package Adverts.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import Adverts.task.model.Adverts;
import Adverts.task.service.AdvertsService;

import java.util.List;

@RestController
@RequestMapping("/adverts")
public class AdvertsController {

    private final AdvertsService advertsService;

    @Autowired
    public AdvertsController(AdvertsService advertsService) {
        this.advertsService = advertsService;
    }

    @GetMapping
    public List<Adverts> getAllAdverts(@RequestParam(value = "sortBy", required = false) String sortBy) {
        return advertsService.getAllAdverts(sortBy);
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

    @PostMapping
    public ResponseEntity<?> createAdvert(@RequestBody Adverts advert, BindingResult result) {
        if (advert.getPrice() < 0 || advert.getMileage() < 0) {
            return ResponseEntity.badRequest().body("Price and mileage must not be negative.");
        }

        Adverts createdAdvert = advertsService.createAdvert(advert);
        return ResponseEntity.status(201).body(createdAdvert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdvert(@PathVariable int id, @RequestBody Adverts advertDetails) {
        Adverts updatedAdvert = advertsService.updateAdvert(id, advertDetails);
        if (updatedAdvert.getPrice() < 0 || updatedAdvert.getMileage() < 0 || updatedAdvert.getId() < 0) {
            return ResponseEntity.unprocessableEntity().body("Price, mileage, and ID must not be negative.");
        }
        if (updatedAdvert != null) {
            return ResponseEntity.ok(updatedAdvert);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvert(@PathVariable int id) {
        boolean isDeleted = advertsService.deleteAdvert(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
