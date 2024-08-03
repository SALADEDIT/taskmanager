package ru.salad.taskmanager.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.entity.Company;
import ru.salad.taskmanager.taskmanager.entity.Status;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.services.TaskService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/tasks", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/getTask")
    public Optional<Task> getTaskById(Integer id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/getTaskByCompany")
    public List<Task> getAllTaskByCompanyId(Integer id) {
        return taskService.getAllTaskByCompanyId(id);
    }

    @PostMapping("/updateTask")
    public Task updateTaskStatus(Integer id, @RequestParam Status status) {
        return taskService.updateTaskStatus(id, status);
    }

    @DeleteMapping("/deleteTask")
    public void deleteTask(Integer id) {
        taskService.deleteTask(id);
    }

    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
}
