package ru.salad.taskmanager.taskmanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.dto.GroupDTO;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.services.GroupService;
import ru.salad.taskmanager.taskmanager.util.groupUtil.GroupErrorResponse;
import ru.salad.taskmanager.taskmanager.util.groupUtil.GroupNameAlreadyExistsException;
import ru.salad.taskmanager.taskmanager.util.groupUtil.GroupNotFoundException;
import ru.salad.taskmanager.taskmanager.util.taskUtil.TaskErrorResponse;
import ru.salad.taskmanager.taskmanager.util.taskUtil.TaskNotFoundException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/company", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GroupController {


    private final GroupService service;

    @GetMapping("{id}")
    public ResponseEntity<GroupDTO> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<GroupDTO> create(@Valid @RequestBody GroupDTO groupDTO) {
        return service.create(groupDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<GroupDTO> update(@PathVariable Integer id, @RequestBody GroupDTO groupDTO) {
        return service.update(id, groupDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @ExceptionHandler
    private ResponseEntity<GroupErrorResponse> notFoundException(GroupNotFoundException exception) {
        GroupErrorResponse response = new GroupErrorResponse(
                "Группа с этим ID не найдена",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    private ResponseEntity<GroupErrorResponse> nameAlreadyExistsException(GroupNameAlreadyExistsException exception) {
        GroupErrorResponse response = new GroupErrorResponse(
                "Группа с таким именем уже существует",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
