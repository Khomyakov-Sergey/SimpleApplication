package ru.clevertec.simpleapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.simpleapplication.client.ApiFeignClient;
import ru.clevertec.simpleapplication.entity.Student;
import ru.clevertec.simpleapplication.entity.User;
import ru.clevertec.simpleapplication.service.ApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/converter")
public class ApiController {

    private final ApiService apiService;
    private final ApiFeignClient feign;

    @GetMapping()
    public Student convertToStudent(@RequestBody User user) {
        return apiService.convertToStudent(user);
    }

    @PostMapping(value = "/send")
    public String sendRequest(@RequestBody User user) {
         return feign.registerUser(user);
    }

   }
