package com.example.dremuk1.services;

import com.example.dremuk1.DTOs.AttendanceDTO;
import com.example.dremuk1.models.Attendance;
import com.example.dremuk1.models.Employee;
import com.example.dremuk1.models.User;
import com.example.dremuk1.repos.AttendanceRepo;
import com.example.dremuk1.repos.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class AttendanceService {
    private final AttendanceRepo attRepo;
    private final EmployeeRepo empRepo;

    public AttendanceService(AttendanceRepo attRepo, EmployeeRepo empRepo) {
        this.attRepo = attRepo;
        this.empRepo = empRepo;
    }
    public Attendance createNewAttendance(AttendanceDTO attendance) {
        Employee employee = empRepo.findByName((attendance.employee_name())).orElseThrow
                (() -> new EntityNotFoundException("Employee not found with id: " + attendance.employee_name()));
        Attendance att = new Attendance();
        att.setEmployee(employee);
        if (attendance.timeIn() != null && attendance.timeOut() != null) {
            att.setWorkHours(calculateWorkHours(attendance.timeIn(), attendance.timeOut()));
        }
        return attRepo.save(att);
    }
    public String closeAttendance(User attendingUser) {
        return null;
    }
    public Double calculateWorkHours(LocalDateTime timeIn, LocalDateTime timeOut) {
        if (timeIn == null || timeOut == null) {
            return null;
        }

        long minutes = ChronoUnit.MINUTES.between(timeIn, timeOut);
        return minutes / 60.0;
    }
    /*Queries*/
}
