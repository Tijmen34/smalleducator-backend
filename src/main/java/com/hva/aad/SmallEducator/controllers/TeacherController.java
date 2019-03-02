package com.hva.aad.SmallEducator.controllers;

import com.hva.aad.SmallEducator.models.ClassModel;
import com.hva.aad.SmallEducator.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {


    private final TeacherService teacherService;

    @Autowired
    public TeacherController(final TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(value = "/class")
    public ResponseEntity<?> createClass(@RequestBody ClassModel classModel) {
        return teacherService.createClass(classModel);
    }
}
