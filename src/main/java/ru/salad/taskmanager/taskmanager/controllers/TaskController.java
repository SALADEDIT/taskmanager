package ru.salad.taskmanager.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.services.TaskService;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/task", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping("{id}")
    public Optional<Task> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public Task update(@PathVariable Integer id, @RequestBody Task request) {
        return service.update(id, request);
    }

    @DeleteMapping
    public void delete(Integer id) {
        service.deleteTask(id);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task createdTask = service.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping
    public Page<Task> getPage(
            @RequestParam Integer companyId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "status") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return service.getTasks(companyId, page, size, sortBy, sortDirection);
    }
}
