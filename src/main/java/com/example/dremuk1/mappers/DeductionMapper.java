package com.example.dremuk1.mappers;

import com.example.dremuk1.DTOs.DeductionDTO;
import com.example.dremuk1.models.Deduction;
import com.example.dremuk1.models.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DeductionMapper {
    @Mapping(source = "employee.id", target = "employeeId")
    DeductionDTO toDTO(Deduction entity);

    @Mapping(target = "employee", expression = "java(employee)")
    @Mapping(target = "id", ignore = true)
    Deduction toEntity(DeductionDTO dto, Employee employee);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DeductionDTO dto, @MappingTarget Deduction entity);
}
