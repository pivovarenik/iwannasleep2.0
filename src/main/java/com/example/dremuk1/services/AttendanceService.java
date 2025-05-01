package com.example.dremuk1.services;

import com.example.dremuk1.DTOs.AttendanceDTO;
import com.example.dremuk1.mappers.AttendanceMapper;
import com.example.dremuk1.models.Attendance;
import com.example.dremuk1.models.Employee;
import com.example.dremuk1.models.User;
import com.example.dremuk1.repos.AttendanceRepo;
import com.example.dremuk1.repos.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepo attendanceRepository;
    private final EmployeeRepo employeeRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceService(AttendanceRepo attendanceRepository, EmployeeRepo employeeRepository, AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.attendanceMapper = attendanceMapper;
    }

    public Attendance createNewAttendance(AttendanceDTO dto) {
        Employee employee = employeeRepository.findByName(dto.employee_name())
                .orElseThrow(() -> new EntityNotFoundException("Employee with name " + dto.employee_name() + " not found"));

        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setTimeIn(dto.timeIn());
        attendance.setTimeOut(dto.timeOut());
        attendance.setWorkHours(dto.workHours());
        attendance.setDate(dto.timeIn().toLocalDate());

        return attendanceRepository.save(attendance);
    }

    public Attendance getById(Integer id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance with id " + id + " not found"));
    }

    public List<Attendance> getAll() {
        return attendanceRepository.findAll();
    }

    public Attendance updateAttendance(Integer id, AttendanceDTO dto) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance with id " + id + " not found"));

        attendanceMapper.updateAttendance(dto, attendance);
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Integer id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance with id " + id + " not found"));
        attendanceRepository.delete(attendance);
    }

}