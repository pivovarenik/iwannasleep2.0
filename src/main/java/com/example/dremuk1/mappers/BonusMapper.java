package com.example.dremuk1.mappers;

import com.example.dremuk1.DTOs.BonusDTO;
import com.example.dremuk1.models.Bonus;
import com.example.dremuk1.models.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BonusMapper {
    @Mapping(source = "employee.id", target = "employeeId")
    BonusDTO toDTO(Bonus entity);

    @Mapping(target = "employee", expression = "java(employee)")
    @Mapping(target = "id", ignore = true)
    Bonus toEntity(BonusDTO dto, Employee employee);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(BonusDTO dto, @MappingTarget Bonus entity);
}
