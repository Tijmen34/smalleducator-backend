package com.hva.aad.SmallEducator.controllers;

import com.hva.aad.SmallEducator.models.StudentModel;
import com.hva.aad.SmallEducator.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller that receives all the HTTP requests on the student property.
 * TODO: Fix student route logic
 *
 * @author Tijmen Stor & Burak Inan
 */
@RestController
public class StudentController {

    private final StudentService studentService;

    /**
     * Constructor to inject StudentService
     * @param studentService layer that communicates with the repository.
     */
    public StudentController (final StudentService studentService) {
        this.studentService = studentService;
    }


    /**
     * Endpoint to create a student.
     * @param studentModel request object containing information of the student.
     * @return student id if the student was successfully created.
     */
    @PostMapping(value = "/student")
    public ResponseEntity<?> createClass(@RequestBody StudentModel studentModel) {
        return studentService.createStudent(studentModel);
    }
}
