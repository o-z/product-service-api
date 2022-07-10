package com.example.productserviceapi.controller;

import com.example.productserviceapi.model.dto.request.SaveUserRequest;
import com.example.productserviceapi.model.entity.UserNodeEntity;
import com.example.productserviceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Map<UUID, UserNodeEntity>> getAllUser(
            @RequestParam("page") Integer page,
            @RequestParam("Size") Integer size) {
        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserNodeEntity> getAllUser(@PathVariable final UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping()
    public ResponseEntity<UserNodeEntity>  saveUser(@RequestBody final SaveUserRequest request) {
        return ResponseEntity.ok( userService.saveUser(request));
    }

}