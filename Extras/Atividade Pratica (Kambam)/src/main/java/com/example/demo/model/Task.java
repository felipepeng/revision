package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String status = "A Fazer"; // Default: "A Fazer"

    @Column(nullable = false)
    private String priority = "Média"; // Default: "Média"

    private LocalDate creationDate = LocalDate.now();

    private LocalDate deadline;
}
