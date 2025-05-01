package com.example.dremuk1.DTOs;

public record ComplaintDTO(
        Integer id,
        Integer userId,
        String message,
        String response,
        String status
) {}
