package com.example.dremuk1.mappers;

import com.example.dremuk1.DTOs.EmployeeDTO;
import com.example.dremuk1.models.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "username", source = "user.username")
    EmployeeDTO employeeToDTO(Employee employee);

    @Mapping(target = "user", expression = "java(null)") // заполним вручную в сервисе
    Employee dtoToEmployee(EmployeeDTO dto);
    @Mapping(target="id",ignore = true)
    void updateEmployeeFromDTO(EmployeeDTO dto, @MappingTarget Employee employee);
}
