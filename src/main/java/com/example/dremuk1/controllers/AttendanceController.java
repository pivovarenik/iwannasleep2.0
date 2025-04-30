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
        return new ResponseEntity<Object>(ResponseEntity.status(HttpStatus.CREATED).body(attMapper.attendanceToAttendanceDTO(freshAttendance)), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAttendance(@PathVariable Integer id,@RequestBody AttendanceDTO attendance) {

    }
}
