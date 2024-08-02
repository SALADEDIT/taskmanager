package ru.salad.taskmanager.taskmanager.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;

@Entity
@Table(name = "Task")
public class TaskModel {

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

    @Column(name = "status")
    private Status status;




}
