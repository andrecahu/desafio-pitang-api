package com.pitang.desafiopitangapi.controllers;

import com.pitang.desafiopitangapi.dto.RegisterRequestDTO;
import com.pitang.desafiopitangapi.service.UserService;
import lombok.RequiredArgsConstructor;

import com.pitang.desafiopitangapi.dto.ResponseDTO;
import com.pitang.desafiopitangapi.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) throws BusinessException {
        return ResponseEntity.ok(userService.register(body));
    }
}
