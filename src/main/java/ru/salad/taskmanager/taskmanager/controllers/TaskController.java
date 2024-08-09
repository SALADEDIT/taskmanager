package ru.salad.taskmanager.taskmanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.dto.task.GetTaskDTO;
import ru.salad.taskmanager.taskmanager.dto.task.PostTaskDTO;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.services.TaskService;
import ru.salad.taskmanager.taskmanager.util.taskUtil.TaskErrorResponse;
import ru.salad.taskmanager.taskmanager.util.taskUtil.TaskNotFoundException;

import java.time.Instant;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/task", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetTaskDTO> update(@PathVariable Integer id, @Valid @RequestBody PostTaskDTO postTaskDTO) {
        return new ResponseEntity<>(service.update(id, postTaskDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<GetTaskDTO> create(@Valid @RequestBody PostTaskDTO postTaskDTO) {
        return new ResponseEntity<>(service.create(postTaskDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public Page<Task> getPage(
            @RequestParam Integer companyId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "status") String sortBy,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(required = false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startDate,
            @RequestParam(required = false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant endDate
    ) {
        // Pageable pageable = service.preparePageable(page, size, sortBy, filter, sortDirection, startDate, endDate);
        // service.getPages(companyId, pageable);
        return service.getPages(companyId, page, size, sortBy, filter, sortDirection, startDate, endDate);
    }

    @ExceptionHandler
    private ResponseEntity<TaskErrorResponse> handleException(TaskNotFoundException exception) {
        TaskErrorResponse response = new TaskErrorResponse(
                "Таска с этим ID не найдена",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
