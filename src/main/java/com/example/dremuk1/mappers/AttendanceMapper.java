package com.example.dremuk1.mappers;

import com.example.dremuk1.DTOs.AttendanceDTO;
import com.example.dremuk1.models.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);
    @Mapping(source="employee.name", target="employee_name")
    @Mapping(source="employee.position", target="employee_position")
    AttendanceDTO attendanceToAttendanceDTO(Attendance attendance);
    @Mapping(target = "id", ignore = true)
    void updateAttendance(AttendanceDTO attendanceDTO, @MappingTarget Attendance attendance);
}
