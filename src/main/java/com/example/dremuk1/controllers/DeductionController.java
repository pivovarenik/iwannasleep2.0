package com.example.dremuk1.controllers;

import com.example.dremuk1.DTOs.DeductionDTO;
import com.example.dremuk1.services.DeductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deductions")
@RequiredArgsConstructor
public class DeductionController {
    private final DeductionService service;
    @GetMapping
    public ResponseEntity<List<DeductionDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeductionDTO> get(@PathVariable Integer id) {
        DeductionDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DeductionDTO> create(@RequestBody DeductionDTO dto) {
        DeductionDTO createdDto = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeductionDTO> update(@PathVariable Integer id, @RequestBody DeductionDTO dto) {
        DeductionDTO updatedDto = service.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
