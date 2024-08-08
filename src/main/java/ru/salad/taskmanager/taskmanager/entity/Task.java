package ru.salad.taskmanager.taskmanager.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "tm_task")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "dead_line")
    private Instant deadLine;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private Group group;

    public Task(String title, String description, Instant deadLine, Status status, Group group) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.status = status;
        this.group = group;
    }
}
