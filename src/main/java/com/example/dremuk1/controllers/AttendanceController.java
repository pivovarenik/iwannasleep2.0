package com.example.dremuk1.controllers;


import com.example.dremuk1.DTOs.AttendanceDTO;
import com.example.dremuk1.mappers.AttendanceMapper;
import com.example.dremuk1.models.Attendance;
import com.example.dremuk1.models.Employee;
import com.example.dremuk1.services.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attService;
    private final AttendanceMapper attMapper;

    public AttendanceController(AttendanceService attService, AttendanceMapper attMapper) {
        this.attService = attService;
        this.attMapper = attMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAttendance(@RequestBody AttendanceDTO attendance) {
        Attendance freshAttendance = attService.createNewAttendance(attendance);
        return new ResponseEntity<>(attMapper.attendanceToAttendanceDTO(freshAttendance), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAttendance(@PathVariable Integer id, @RequestBody AttendanceDTO attendance) {
        return ResponseEntity.status(HttpStatus.OK).body(attMapper.attendanceToAttendanceDTO(attService.updateAttendance(id, attendance)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable Integer id) {
        Attendance attendance = attService.getById(id);
        return ResponseEntity.ok(attMapper.attendanceToAttendanceDTO(attendance));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
        List<Attendance> attendances = attService.getAll();
        List<AttendanceDTO> dtos = attendances.stream()
                .map(attMapper::attendanceToAttendanceDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Integer id) {
        attService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
