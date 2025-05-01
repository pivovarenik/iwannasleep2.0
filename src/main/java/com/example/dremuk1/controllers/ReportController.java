package com.example.dremuk1.controllers;

import com.example.dremuk1.DTOs.ReportDTO;
import com.example.dremuk1.models.Report;
import com.example.dremuk1.services.ReportService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("")
    public ResponseEntity<?> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @PostMapping()
    public ResponseEntity<?> addReport(@RequestBody ReportDTO report) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reportService.createReport(report));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReport(@PathVariable Integer id, @RequestBody ReportDTO report) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.updateReport(id,report));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable Integer id) {
        reportService.deleteReport(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
