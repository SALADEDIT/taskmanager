package ru.salad.taskmanager.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.entity.Status;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.repositories.TaskRepository;
import ru.salad.taskmanager.taskmanager.services.CompanyService;
import ru.salad.taskmanager.taskmanager.services.TaskService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/tasks", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final CompanyService companyService;

    @GetMapping("/getTask")
    public Optional<Task> getTaskById(Integer id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/getTaskByCompany")
    public List<Task> getAllTaskByCompanyId(Integer id) {
        return taskService.getAllTaskByCompanyId(id);
    }

//    @GetMapping("/deleteTask")
//    public Task deleteTaskById(Integer id) {
//        return taskService.deleteTaskById(id);
//    }

//    @PostMapping("/updateTask")
//    public Task updateTaskStatus(@RequestBody Task task, Integer id, Status newStatus) {
//        task.setStatus("CLOSED");
//    }

//    @PostMapping("/update")
//    public Task updateTask(@RequestBody Task task) {
//        task.setTitle("ahahahaha");
//        return task;
//    }
}
