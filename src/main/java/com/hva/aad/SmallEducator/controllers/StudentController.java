package com.hva.aad.SmallEducator.controllers;

import com.hva.aad.SmallEducator.models.request.CreateStudentRequestModel;
import com.hva.aad.SmallEducator.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller that receives all the HTTP requests on the student property.
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
    @Autowired
    public StudentController (final StudentService studentService) {
        this.studentService = studentService;
    }


    /**
     * Endpoint to create a student.
     * @param createStudentRequestModel request object containing information of the student.
     * @return student id if the student was successfully created.
     */
    @PostMapping(value = "/student")
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequestModel createStudentRequestModel) {
        return studentService.createStudent(createStudentRequestModel);
    }

    @GetMapping(value = "/student/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable int studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping(value = "/student/{entryCode}")
    public ResponseEntity<?> checkEntryCode(@PathVariable("entryCode") String entryCode) {
        return studentService.checkEntryCode(entryCode);
    }

    @GetMapping(value = "/students")
    public ResponseEntity<?> getAllStudents() {
        return studentService.getAllStudents();
    }
}
