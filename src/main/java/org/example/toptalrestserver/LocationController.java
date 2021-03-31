package org.example.toptalrestserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Validated
public class LocationController {

    @Autowired
    private LocationRepository repository;

    @GetMapping("/locations")
    List<Location> findAllLocations() {
        return repository.findAll();
    }

    @GetMapping("/locations/{id}")
    Location findLocation(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @PostMapping("/locations")
    @ResponseStatus(HttpStatus.CREATED)
    Location newLocation(@RequestBody Location newLocation) {
        return repository.save(newLocation);
    }

    @PutMapping("/locations/{id}")
    Location saveOrUpdateLocation(@RequestBody Location newLocation, @PathVariable Long id) {
        return repository.findById(id)
                .map(x -> {
                    x.setName(newLocation.getName());
                    x.setLat(newLocation.getLat());
                    x.setLng(newLocation.getLng());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newLocation.setId(id);
                    return repository.save(newLocation);
                });
    }

    @DeleteMapping("/locations/{id}")
    void deleteLocation(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
