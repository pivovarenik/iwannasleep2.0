package com.example.dremuk1.DTOs;

public record EmployeeDTO(
        Integer id,
        String username,
        String name,
        String position,
        Double hourlyRate,
        String bankAccount
) {}
