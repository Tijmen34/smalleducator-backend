package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.CourseStudentDao;
import com.hva.aad.SmallEducator.dao.StudentDao;
import com.hva.aad.SmallEducator.models.CourseStudentModel;
import com.hva.aad.SmallEducator.models.StudentModel;
import com.hva.aad.SmallEducator.requestmodels.CourseStudentResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for the student property to communicate with the repository.
 * TODO: Create the logic for routes.
 *
 * @author Burak Inan & Tijmen Stor
 */
@Service
public class StudentService {

    private final StudentDao studentDao;
    private final CourseStudentDao courseStudentDao;

    @Autowired
    public StudentService(final StudentDao studentDao, final CourseStudentDao courseStudentDao) {
        this.studentDao = studentDao;
        this.courseStudentDao = courseStudentDao;
    }

    public ResponseEntity<?> createStudent(final StudentModel studentModel) {
        if (studentModel.getFirstName() == null ||
                studentModel.getLastName() == null ||
                studentModel.getMailAddress() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        } else {
            studentDao.save(studentModel);
            return new ResponseEntity<>(studentModel.getId(), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> checkEntryCode(String entryCode) {
        if (entryCode == null) {
            return new ResponseEntity<>("No code provided!", HttpStatus.BAD_REQUEST);
        } else {
            final Optional<CourseStudentModel> courseStudentModel = courseStudentDao.findByStudentEntryCode(entryCode);
            if (!courseStudentModel.isPresent()) {
                return new ResponseEntity<>("Server failed to retrieve information", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                final CourseStudentResponseModel courseStudentResponseModel = CourseStudentResponseModel
                        .builder()
                        .courseCode(courseStudentModel.get().getCourse().getCourseCode())
                        .courseName(courseStudentModel.get().getCourse().getCourseName())
                        .courseDescription(courseStudentModel.get().getCourse().getCourseDescription())
                        .firstName(courseStudentModel.get().getStudent().getFirstName())
                        .lastName(courseStudentModel.get().getStudent().getLastName())
                        .build();
                return new ResponseEntity<>(courseStudentResponseModel, HttpStatus.OK);
            }
        }
    }
}
