package com.hva.aad.SmallEducator.controllers;

import com.hva.aad.SmallEducator.models.TeacherModel;
import com.hva.aad.SmallEducator.models.request.CreateTeacherRequestModel;
import com.hva.aad.SmallEducator.models.request.TeacherLoginRequestModel;
import com.hva.aad.SmallEducator.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller to receive HTTP requests on the teacher property.
 *
 * @author Tijmen Stor & Burak Inan
 */
@RequestMapping("teacher")
@RestController
public class TeacherController {

    private final TeacherService teacherService;

    /**
     * Constructor to inject TeacherService.
     *
     * @param teacherService layer that communicates with the repository.
     */
    @Autowired
    public TeacherController(final TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    /**
     * Endpoint to create a teacher.
     *
     * @param teacherModel contains information about the new teacher.
     * @return teacher ID if the teacher is successfully created.
     */
    @PostMapping(value = "/")
    public ResponseEntity<?> createTeacher(@RequestBody CreateTeacherRequestModel createTeacherRequestModel) {
        return teacherService.createTeacher(createTeacherRequestModel);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginTeacher(@RequestBody TeacherLoginRequestModel teacherLoginRequestModel) {
        return teacherService.loginTeacher(teacherLoginRequestModel);
    }
}
