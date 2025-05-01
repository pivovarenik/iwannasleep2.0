package com.example.dremuk1.mappers;

import com.example.dremuk1.DTOs.ComplaintDTO;
import com.example.dremuk1.models.Complaint;
import com.example.dremuk1.models.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {
    @Mapping(source = "user.id", target = "userId")
    ComplaintDTO toDTO(Complaint complaint);

    @Mapping(target = "user", expression = "java(user)")
    @Mapping(target = "id", ignore = true)
    Complaint toEntity(ComplaintDTO dto, User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateComplaintFromDTO(ComplaintDTO dto, @MappingTarget Complaint complaint);
}
