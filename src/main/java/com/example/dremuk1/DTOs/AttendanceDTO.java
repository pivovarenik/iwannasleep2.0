package com.example.dremuk1.DTOs;

import java.time.LocalDateTime;

public record AttendanceDTO(
        Integer id,
        String employee_name,
        String employee_position,
        LocalDateTime timeIn,
        LocalDateTime timeOut,
        Double workHours
)
{}
