package com.example.dremuk1.controllers;

import com.example.dremuk1.DTOs.PayrollDTO;
import com.example.dremuk1.services.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payrolls")
@RequiredArgsConstructor
public class PayrollController {
    private final PayrollService service;

    @GetMapping
    public ResponseEntity<List<PayrollDTO>> getAll() {
        List<PayrollDTO> payrolls = service.getAll();
        return ResponseEntity.ok(payrolls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayrollDTO> get(@PathVariable Integer id) {
        PayrollDTO payroll = service.getById(id);
        return ResponseEntity.ok(payroll);
    }

    @PostMapping
    public ResponseEntity<PayrollDTO> create(@RequestBody PayrollDTO dto) {
        PayrollDTO createdPayroll = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayroll);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PayrollDTO> update(@PathVariable Integer id, @RequestBody PayrollDTO dto) {
        PayrollDTO updatedPayroll = service.update(id, dto);
        return ResponseEntity.ok(updatedPayroll);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}