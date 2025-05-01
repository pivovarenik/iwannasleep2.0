package com.example.dremuk1.services;

import com.example.dremuk1.DTOs.ComplaintDTO;
import com.example.dremuk1.mappers.ComplaintMapper;
import com.example.dremuk1.models.Complaint;
import com.example.dremuk1.models.User;
import com.example.dremuk1.repos.ComplaintRepo;
import com.example.dremuk1.repos.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepo complaintRepo;
    private final ComplaintMapper mapper;
    private final UserRepo userRepo;

    public List<ComplaintDTO> getAll() {
        return complaintRepo.findAll().stream().map(mapper::toDTO).toList();
    }

    public ComplaintDTO getById(Integer id) {
        Complaint complaint = complaintRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        return mapper.toDTO(complaint);
    }

    public ComplaintDTO create(ComplaintDTO dto) {
        User user = userRepo.findById(dto.userId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Complaint complaint = mapper.toEntity(dto, user);
        return mapper.toDTO(complaintRepo.save(complaint));
    }

    public ComplaintDTO update(Integer id, ComplaintDTO dto) {
        Complaint existing = complaintRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        mapper.updateComplaintFromDTO(dto, existing);
        return mapper.toDTO(complaintRepo.save(existing));
    }

    public void delete(Integer id) {
        complaintRepo.deleteById(id);
    }
}
