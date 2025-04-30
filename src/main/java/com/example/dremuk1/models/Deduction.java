package com.example.dremuk1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "deductions")
public class Deduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deduction_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "deduction_date", nullable = false)
    private LocalDate deductionDate;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Lob
    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "description")
    private String description;

}