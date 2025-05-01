package com.example.dremuk1.services;

import com.example.dremuk1.DTOs.PayrollDTO;
import com.example.dremuk1.mappers.PayrollMapper;
import com.example.dremuk1.models.Employee;
import com.example.dremuk1.models.Payroll;
import com.example.dremuk1.models.User;
import com.example.dremuk1.repos.EmployeeRepo;
import com.example.dremuk1.repos.PayrollRepo;
import com.example.dremuk1.repos.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollService {
    private final PayrollRepo repo;
    private final EmployeeRepo employeeRepo;
    private final UserRepo userRepo;
    private final PayrollMapper mapper;

    public List<PayrollDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public PayrollDTO getById(Integer id) {
        return mapper.toDTO(repo.findById(id).orElseThrow());
    }

    public PayrollDTO create(PayrollDTO dto) {
        Employee emp = employeeRepo.findById(dto.employeeId()).orElseThrow();
        User approver = dto.approvedById() != null ? userRepo.findById(dto.approvedById()).orElse(null) : null;
        Payroll entity = mapper.toEntity(dto, emp, approver);
        return mapper.toDTO(repo.save(entity));
    }

    public PayrollDTO update(Integer id, PayrollDTO dto) {
        Payroll entity = repo.findById(id).orElseThrow();
        mapper.updateFromDTO(dto, entity);
        return mapper.toDTO(repo.save(entity));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
