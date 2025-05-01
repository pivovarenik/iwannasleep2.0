package com.example.dremuk1.services;

import com.example.dremuk1.DTOs.BonusDTO;
import com.example.dremuk1.mappers.BonusMapper;
import com.example.dremuk1.models.Bonus;
import com.example.dremuk1.models.Employee;
import com.example.dremuk1.repos.BonusRepo;
import com.example.dremuk1.repos.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BonusService {
    private final BonusRepo repo;
    private final EmployeeRepo employeeRepo;
    private final BonusMapper mapper;

    public List<BonusDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public BonusDTO getById(Integer id) {
        return mapper.toDTO(repo.findById(id).orElseThrow());
    }

    public BonusDTO create(BonusDTO dto) {
        Employee emp = employeeRepo.findById(dto.employeeId()).orElseThrow();
        Bonus entity = mapper.toEntity(dto, emp);
        return mapper.toDTO(repo.save(entity));
    }

    public BonusDTO update(Integer id, BonusDTO dto) {
        Bonus entity = repo.findById(id).orElseThrow();
        mapper.updateFromDTO(dto, entity);
        return mapper.toDTO(repo.save(entity));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
