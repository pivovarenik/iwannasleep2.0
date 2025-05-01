package com.example.dremuk1.repos;

import com.example.dremuk1.models.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusRepo extends JpaRepository<Bonus, Integer> {
}
