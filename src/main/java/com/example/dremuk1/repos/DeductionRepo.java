package com.example.dremuk1.repos;

import com.example.dremuk1.models.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeductionRepo extends JpaRepository<Deduction, Integer> {
}
