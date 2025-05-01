package com.example.dremuk1.DTOs;

import java.time.LocalDateTime;

public record ReportDTO(
        Integer id,
        String creator_username,
        String reportType,
        LocalDateTime creationDate,
        String filePath
) {
}
