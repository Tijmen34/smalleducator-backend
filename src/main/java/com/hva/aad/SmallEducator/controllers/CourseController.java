package com.hva.aad.SmallEducator.controllers;

import com.hva.aad.SmallEducator.models.CourseModel;
import com.hva.aad.SmallEducator.requestmodels.CourseStudentAPIModel;
import com.hva.aad.SmallEducator.requestmodels.CreateCourseModel;
import com.hva.aad.SmallEducator.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller that receives all the HTTP requests on the course property.
 *
 * @author Tijmen Stor & Burak Inan
 */
@RestController
public class CourseController {

    private final CourseService courseService;

    /**
     * Constructor to inject CourseService
     * @param courseService layer that communicates with the repository.
     */
    @Autowired
    public CourseController (final CourseService courseService) {
        this.courseService = courseService;
    }


    /**
     * Endpoint to create a course.
     * @param courseModel request object containing information of the course.
     * @return course id if the course was successfully created.
     */
    @PostMapping(value = "/course")
    public ResponseEntity<?> createClass(@RequestBody CreateCourseModel courseModel) {
        return courseService.createCourse(courseModel);
    }

    /**
     * Endpoint to add a student to a course.
     * @param courseStudentAPIModel request object containing information about the course and the student.
     * @return the entry code if the student was successfully added.
     */
    @PostMapping(value = "/course/student")
    public ResponseEntity<?> addStudentToCourse(@RequestBody CourseStudentAPIModel courseStudentAPIModel) {
        return courseService.addStudentToCourse(courseStudentAPIModel);
    }
}