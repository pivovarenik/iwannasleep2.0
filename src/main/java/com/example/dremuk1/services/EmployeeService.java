package com.example.dremuk1.services;

import com.example.dremuk1.DTOs.EmployeeDTO;
import com.example.dremuk1.models.Employee;
import com.example.dremuk1.models.User;
import com.example.dremuk1.mappers.EmployeeMapper;
import com.example.dremuk1.repos.EmployeeRepo;
import com.example.dremuk1.repos.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final UserRepo userRepo;
    private final EmployeeMapper mapper;

    public List<EmployeeDTO> getAll() {
        return employeeRepo.findAll().stream()
                .map(mapper::employeeToDTO)
                .toList();
    }

    public EmployeeDTO getById(Integer id) {
        return employeeRepo.findById(id)
                .map(mapper::employeeToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " not found"));
    }

    public EmployeeDTO create(EmployeeDTO dto) {
        Employee employee = mapper.dtoToEmployee(dto);
        User user = userRepo.findByUsername(dto.username())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + dto.username()));
        employee.setUser(user);
        return mapper.employeeToDTO(employeeRepo.save(employee));
    }

    public EmployeeDTO update(Integer id, EmployeeDTO dto) {
        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found: " + id));

        mapper.updateEmployeeFromDTO(dto, existing);
        if (dto.username() != null) {
            User user = userRepo.findByUsername(dto.username())
                    .orElseThrow(() -> new EntityNotFoundException("User not found: " + dto.username()));
            existing.setUser(user);
        }

        return mapper.employeeToDTO(employeeRepo.save(existing));
    }

    public void delete(Integer id) {
        if (!employeeRepo.existsById(id)) {
            throw new EntityNotFoundException("Employee with id " + id + " not found");
        }
        employeeRepo.deleteById(id);
    }
}
