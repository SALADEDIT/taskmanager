package ru.salad.taskmanager.taskmanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.dto.group.GetGroupDTO;
import ru.salad.taskmanager.taskmanager.dto.group.PostGroupDTO;
import ru.salad.taskmanager.taskmanager.services.GroupService;
import ru.salad.taskmanager.taskmanager.util.groupUtil.GroupErrorResponse;
import ru.salad.taskmanager.taskmanager.util.groupUtil.GroupNotFoundException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/group", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GroupController {


    private final GroupService service;

    @GetMapping("/{id}")
    public ResponseEntity<GetGroupDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GetGroupDTO> create(@Valid @RequestBody PostGroupDTO postGroupDTO) {
        return new ResponseEntity<>(service.create(postGroupDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetGroupDTO> update(@PathVariable Integer id, @Valid @RequestBody PostGroupDTO postGroupDTO) {
        return new ResponseEntity<>(service.update(id, postGroupDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
            return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    private ResponseEntity<GroupErrorResponse> notFoundException(GroupNotFoundException exception) {
        GroupErrorResponse response = new GroupErrorResponse(
                "Группа с этим ID не найдена",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

//    @ExceptionHandler
//    private ResponseEntity<GroupErrorResponse> nameAlreadyExistsException(GroupNameAlreadyExistsException exception) {
//        GroupErrorResponse response = new GroupErrorResponse(
//                "Группа с таким именем уже существует",
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//
//    }
}
