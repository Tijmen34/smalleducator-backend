package com.hva.aad.SmallEducator.controllers;

import com.hva.aad.SmallEducator.models.request.CourseStudentRequestModel;
import com.hva.aad.SmallEducator.models.request.CreateCourseRequestModel;
import com.hva.aad.SmallEducator.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<?> createClass(@RequestBody CreateCourseRequestModel courseModel) {
        return courseService.createCourse(courseModel);
    }

    /**
     * Endpoint to add a student to a course.
     * @param courseStudentRequestModel request object containing information about the course and the student.
     * @return the entry code if the student was successfully added.
     */
    @PostMapping(value = "/course/student")
    public ResponseEntity<?> addStudentToCourse(@RequestBody CourseStudentRequestModel courseStudentRequestModel) {
        return courseService.addStudentToCourse(courseStudentRequestModel);
    }

    /**
     * Endpoint to retrieve all the courses.
     */
    @GetMapping(value = "/courses/{teacherId}")
    public ResponseEntity<?> getCoursesByTeacher(@PathVariable int teacherId) {
        return courseService.getCoursesByTeacher(teacherId);
    }

    /**
     * Endpoint to delete a course.
     */
    @DeleteMapping(value = "/course/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId) {
        return courseService.deleteCourse(courseId);
    }
}
