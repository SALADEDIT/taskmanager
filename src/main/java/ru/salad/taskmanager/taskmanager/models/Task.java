package ru.salad.taskmanager.taskmanager.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "Task")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @NotEmpty(message = "Заголовок не должен быть пустым")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "dead_line")
    private Instant deadLine;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
