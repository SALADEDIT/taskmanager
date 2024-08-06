package ru.salad.taskmanager.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.services.GroupService;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/company", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GroupController {


    private final GroupService groupService;

    @GetMapping("/getCompanyById")
    public Optional<Group> getCompanyById(Integer id) {
        return groupService.getCompanyById(id);
    }

    @PostMapping("/createCompany")
    public ResponseEntity<Group> createCompany(@RequestBody Group group) {
        Group createdGroup = groupService.createCompany(group);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PutMapping("/updateCompany")
    public Group updateCompanyName(Integer id, @RequestBody String name) {
        return groupService.updateCompanyName(id, name);
    }

    @DeleteMapping("/deleteCompany")
    public void deleteCompany(Integer id) {
        groupService.deleteCompany(id);
    }
}
