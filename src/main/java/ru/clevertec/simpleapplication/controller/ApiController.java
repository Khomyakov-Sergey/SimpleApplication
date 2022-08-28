package ru.clevertec.simpleapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.simpleapplication.entity.Student;
import ru.clevertec.simpleapplication.entity.User;
import ru.clevertec.simpleapplication.service.ApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/converter")
public class ApiController {

    private final ApiService apiService;

    @GetMapping()
    public Student convertToStudent(@RequestBody User user) {
        return apiService.convertToStudent(user);
    }

   }
