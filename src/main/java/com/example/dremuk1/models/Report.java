package com.example.dremuk1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
    @JsonProperty(value="reportType")
    @Lob
    @Column(name = "report_type", nullable = false)
    private String reportType;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @JsonProperty(value="filePath")
    @Column(name = "file_path")
    private String filePath;

}