package ru.salad.taskmanager.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.models.Task;
import ru.salad.taskmanager.taskmanager.services.TaskService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/tasks", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/getTask")
    public Task getTaskById(Integer id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/update")
    public Task updateTask(@RequestBody Task task) {
        task.setTitle("ahahahahahahahahahahahahahahahahahahahah");
        return task;
    }
}
