package com.example.dremuk1.mappers;


import com.example.dremuk1.DTOs.AttendanceDTO;
import com.example.dremuk1.DTOs.ReportDTO;
import com.example.dremuk1.models.Attendance;
import com.example.dremuk1.models.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);
    @Mapping(target="creator_username", source = "createdBy.username")
    @Mapping(target="creationDate", source = "creationDate")
    ReportDTO reportToReportDTO(Report report);
    @Mapping(target ="createdBy", ignore = true)
    Report reportDTOToReport(ReportDTO reportDTO);
    @Mapping(target="createdBy", ignore = true)
    @Mapping(target="id", ignore = true)
    void updateReport(ReportDTO reportDTO, @MappingTarget Report report);
}
