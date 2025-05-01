package com.example.dremuk1.DTOs;

import java.time.LocalDate;

public record DeductionDTO(
    Integer id,
    Integer employeeId,
    LocalDate deductionDate,
    Double amount,
    String type,
    String description
) {}
