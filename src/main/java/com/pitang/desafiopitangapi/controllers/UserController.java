package com.pitang.desafiopitangapi.controllers;

import com.pitang.desafiopitangapi.dto.UserDTO;
import com.pitang.desafiopitangapi.service.UserService;
import lombok.RequiredArgsConstructor;

import com.pitang.desafiopitangapi.dto.ResponseDTO;
import com.pitang.desafiopitangapi.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> register(@RequestBody UserDTO body) throws BusinessException {
        return ResponseEntity.ok(userService.register(body));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO userDTO) throws BusinessException {
        return ResponseEntity.ok(userService.update(id, userDTO));
    }
}
