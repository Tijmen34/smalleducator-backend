package com.hva.aad.SmallEducator.controllers;

import com.hva.aad.SmallEducator.models.CourseModel;
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
    @PostMapping(value = "/class")
    public ResponseEntity<?> createClass(@RequestBody CourseModel courseModel) {
        return courseService.createCourse(courseModel);
    }
}
