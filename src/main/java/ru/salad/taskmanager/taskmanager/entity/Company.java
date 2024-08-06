package ru.salad.taskmanager.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Task> tasks;

    public Company(Integer id, String name) {
        this.name = name;
        this.id = id;
    }
}
