package com.example.dremuk1.DTOs;

public record PayrollDTO(
    Integer id,
    Integer employeeId,
    Double grossSalary,
    Double deductions,
    Double bonuses,
    Double netSalary,
    Integer approvedById
) {}
