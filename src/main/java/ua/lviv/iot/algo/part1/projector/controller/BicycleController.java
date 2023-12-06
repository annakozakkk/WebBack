package ua.lviv.iot.algo.part1.projector.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.algo.part1.projector.model.Bicycle;
import ua.lviv.iot.algo.part1.projector.dto.BicycleDTO;
import ua.lviv.iot.algo.part1.business.BicycleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/bicycles")
public class BicycleController {
    @Autowired
    private BicycleService bicycleService;

    public BicycleDTO format(final Bicycle bicycle) {
        return new BicycleDTO(
                bicycle.getTitle(),
                bicycle.getText(),
                bicycle.getPrice(),
                bicycle.getType(),
                bicycle.getId(),
                bicycle.getImage()
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllBicycles() {
        List<BicycleDTO> result = new ArrayList<>();
        if (bicycleService.getAllBicycles().isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            for (Bicycle bicycle : bicycleService.getAllBicycles()) {
                result.add(format(bicycle));
            }
            return ResponseEntity.ok(result);
        }
    }
    @GetMapping("/filter")
    public List<Bicycle> filterBicycles(
            @RequestParam(name = "price", defaultValue = "all") String price,
            @RequestParam(name = "name", defaultValue = "all") String name,
            @RequestParam(name = "type", defaultValue = "all") String type,
            @RequestParam(name= "title", defaultValue = "") String title) {
        try {

            return bicycleService.getFilteredBicycles(name, type, price,title);
        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBicycle(@PathVariable("id") Integer bicycleId) {
        Bicycle bicycle = bicycleService.getBicycle(bicycleId);
        if (bicycle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        BicycleDTO bicycleDto = format(bicycle);
        return ResponseEntity.ok(bicycleDto);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Bicycle bicycle) {
        BicycleDTO bicycleDto = format(bicycleService.createBicycle(bicycle));

        List<BicycleDTO> allBicycles = new ArrayList<>();
        if (bicycleService.getAllBicycles().isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            for (Bicycle existingBicycle : bicycleService.getAllBicycles()) {
                allBicycles.add(format(existingBicycle));
            }
           

        }
        return ResponseEntity.ok(allBicycles);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BicycleDTO> deleteBicycle(@PathVariable("id") Integer bicycleId) {
        if (bicycleService.deleteBicycle(bicycleId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping (path = "/{id}")
    public ResponseEntity<BicycleDTO> updateBicycle(@PathVariable("id") Integer bicycleId,
                                                    @RequestBody Bicycle bicycle) {
        Bicycle updatedBicycle = bicycleService.updateBicycle(bicycleId, bicycle);
        if (updatedBicycle != null) {
            BicycleDTO bicycleDto = format(bicycleService.updateBicycle(bicycleId, bicycle));
            return ResponseEntity.ok(bicycleDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
