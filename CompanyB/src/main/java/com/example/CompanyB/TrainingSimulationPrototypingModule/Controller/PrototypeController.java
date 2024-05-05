package com.example.CompanyB.TrainingSimulationPrototypingModule.Controller;

import com.example.CompanyB.TrainingSimulationPrototypingModule.Model.PrototypeModel;
import com.example.CompanyB.TrainingSimulationPrototypingModule.Service.PrototypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/tps/prototypes")
public class PrototypeController {

    private final PrototypeService prototypeService;

    public PrototypeController(PrototypeService prototypeService) {
        this.prototypeService = prototypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPrototype(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("material") String material,
                                                  @RequestParam("color") String color,
                                                  @RequestParam("shape") String shape,
                                                  @RequestParam("comments") String comments) {
        try {
            String prototypeId = prototypeService.createPrototype(file, material, color, shape, comments);
            return ResponseEntity.ok(prototypeId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create prototype.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process file.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePrototype(@PathVariable String id,
                                                @RequestParam boolean thermalTestPassed,
                                                @RequestParam boolean electricalTestPassed,
                                                @RequestParam String approvalStatus,
                                                @RequestParam String approvalMessage) {
        try {
            prototypeService.updatePrototype(id, thermalTestPassed, electricalTestPassed, approvalStatus, approvalMessage);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrototypeModel> getPrototype(@PathVariable String id) {
        try {
            PrototypeModel prototype = prototypeService.getPrototype(id);
            if (prototype != null) {
                return ResponseEntity.ok(prototype);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrototype(@PathVariable String id) {
        try {
            prototypeService.deletePrototype(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}