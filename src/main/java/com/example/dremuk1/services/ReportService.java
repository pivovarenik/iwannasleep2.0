package com.example.dremuk1.services;

import com.example.dremuk1.DTOs.ReportDTO;
import com.example.dremuk1.mappers.ReportMapper;
import com.example.dremuk1.models.Report;
import com.example.dremuk1.models.User;
import com.example.dremuk1.repos.ReportRepo;
import com.example.dremuk1.repos.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepo reportRepo;
    private final ReportMapper reportMapper;
    private final UserRepo userRepo;

    public List<ReportDTO> getAllReports() {
        return reportRepo.findAll().stream()
                .map(reportMapper::reportToReportDTO)
                .collect(Collectors.toList());
    }

    public ReportDTO getReportById(Integer id) {
        Report report = reportRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report with ID " + id + " not found"));
        return reportMapper.reportToReportDTO(report);
    }

    public ReportDTO createReport(ReportDTO reportDTO) {
        Report report = reportMapper.reportDTOToReport(reportDTO);
        User creator = userRepo.findByUsername(reportDTO.creator_username())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + reportDTO.creator_username()));
        report.setCreatedBy(creator);
        return reportMapper.reportToReportDTO(reportRepo.save(report));
    }

    public ReportDTO updateReport(Integer id, ReportDTO reportDTO) {
        Report existingReport = reportRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report with ID " + id + " not found"));

        reportMapper.updateReport(reportDTO, existingReport);

        if (reportDTO.creator_username() != null) {
            User creator = userRepo.findByUsername(reportDTO.creator_username())
                    .orElseThrow(() -> new EntityNotFoundException("User not found: " + reportDTO.creator_username()));
            existingReport.setCreatedBy(creator);
        }

        return reportMapper.reportToReportDTO(reportRepo.save(existingReport));
    }

    public void deleteReport(Integer id) {
        if (!reportRepo.existsById(id)) {
            throw new EntityNotFoundException("Report with ID " + id + " does not exist");
        }
        reportRepo.deleteById(id);
    }
}
