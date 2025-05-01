package com.example.dremuk1.mappers;

import com.example.dremuk1.DTOs.PayrollDTO;
import com.example.dremuk1.models.Payroll;
import com.example.dremuk1.models.Employee;
import com.example.dremuk1.models.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PayrollMapper {
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "approvedBy.id", target = "approvedById")
    PayrollDTO toDTO(Payroll entity);

    @Mapping(target = "employee", expression = "java(employee)")
    @Mapping(target = "approvedBy", expression = "java(approver)")
    @Mapping(target = "id", ignore = true)
    Payroll toEntity(PayrollDTO dto, Employee employee, User approver);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(PayrollDTO dto, @MappingTarget Payroll entity);
}
