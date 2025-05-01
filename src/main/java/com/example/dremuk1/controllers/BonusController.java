package com.example.dremuk1.controllers;

import com.example.dremuk1.DTOs.BonusDTO;
import com.example.dremuk1.services.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bonuses")
@RequiredArgsConstructor
public class BonusController {
    private final BonusService service;

    @GetMapping
    public ResponseEntity<List<BonusDTO>> getAll() {
        List<BonusDTO> bonuses = service.getAll();
        return ResponseEntity.ok(bonuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonusDTO> getById(@PathVariable Integer id) {
        BonusDTO bonus = service.getById(id);
        return ResponseEntity.ok(bonus);
    }

    @PostMapping
    public ResponseEntity<BonusDTO> create(@RequestBody BonusDTO dto) {
        BonusDTO createdBonus = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBonus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BonusDTO> update(@PathVariable Integer id,
                                           @RequestBody BonusDTO dto) {
        BonusDTO updatedBonus = service.update(id, dto);
        return ResponseEntity.ok(updatedBonus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}