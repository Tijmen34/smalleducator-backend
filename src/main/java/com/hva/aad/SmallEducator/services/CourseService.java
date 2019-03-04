package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.CourseDao;
import com.hva.aad.SmallEducator.models.CourseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service for the course property to communicate with the repository.
 *
 * @author Burak Inan & Tijmen Stor
 */
@Service
public class CourseService {

    private final CourseDao courseDao;

    @Autowired
    public CourseService(final CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public ResponseEntity<?> createCourse(final CourseModel courseModel) {
        if (courseModel.getCourseName() == null ||
                courseModel.getCourseCode() == null ||
                courseModel.getCourseDescription() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        } else {
            if (courseDao.existsByCourseCode(courseModel.getCourseCode())) {
                return new ResponseEntity<>("Course Code already exists.", HttpStatus.CONFLICT);
            } else {
                courseDao.save(courseModel);
                return new ResponseEntity<>(courseModel.getId(), HttpStatus.OK);
            }
        }
    }
}
