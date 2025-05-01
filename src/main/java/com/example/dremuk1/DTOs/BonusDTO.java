package com.example.dremuk1.DTOs;

import java.time.LocalDate;

public record BonusDTO(
    Integer id,
    Integer employeeId,
    LocalDate bonusDate,
    Double bonusAmount,
    String reason
) {}
