package com.example.dremuk1.services;

import com.example.dremuk1.DTOs.DeductionDTO;
import com.example.dremuk1.mappers.DeductionMapper;
import com.example.dremuk1.models.Deduction;
import com.example.dremuk1.models.Employee;
import com.example.dremuk1.repos.DeductionRepo;
import com.example.dremuk1.repos.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeductionService {
    private final DeductionRepo repo;
    private final EmployeeRepo employeeRepo;
    private final DeductionMapper mapper;

    public List<DeductionDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public DeductionDTO getById(Integer id) {
        return mapper.toDTO(repo.findById(id).orElseThrow());
    }

    public DeductionDTO create(DeductionDTO dto) {
        Employee emp = employeeRepo.findById(dto.employeeId()).orElseThrow();
        Deduction entity = mapper.toEntity(dto, emp);
        return mapper.toDTO(repo.save(entity));
    }

    public DeductionDTO update(Integer id, DeductionDTO dto) {
        Deduction entity = repo.findById(id).orElseThrow();
        mapper.updateFromDTO(dto, entity);
        return mapper.toDTO(repo.save(entity));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
