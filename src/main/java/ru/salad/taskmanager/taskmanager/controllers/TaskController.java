package ru.salad.taskmanager.taskmanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.dto.TaskDTO;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.services.TaskService;
import ru.salad.taskmanager.taskmanager.util.taskUtil.TaskErrorResponse;
import ru.salad.taskmanager.taskmanager.util.taskUtil.TaskNotFoundException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/task", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping("{id}")
    public ResponseEntity<Task> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskDTO> update( @PathVariable Integer id, @Valid @RequestBody TaskDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create(@Valid @RequestBody TaskDTO taskDTO) {
        return service.create(taskDTO);
    }

    @GetMapping
    public Page<Task> getPage(
            @RequestParam Integer companyId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "status") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return service.getPages(companyId, page, size, sortBy, sortDirection);
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
