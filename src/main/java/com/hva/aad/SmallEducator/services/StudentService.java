package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.models.request.CreateStudentRequestModel;
import com.hva.aad.SmallEducator.models.response.CourseStudentResponseModel;
import com.hva.aad.SmallEducator.dao.CourseDao;
import com.hva.aad.SmallEducator.dao.CourseStudentDao;
import com.hva.aad.SmallEducator.dao.StudentDao;
import com.hva.aad.SmallEducator.models.Course;
import com.hva.aad.SmallEducator.models.CourseStudent;
import com.hva.aad.SmallEducator.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service for the student property to communicate with the repository.
 * TODO: Create the logic for routes.
 *
 * @author Burak Inan & Tijmen Stor
 */
@Service
public class StudentService {

    private final StudentDao studentDAO;
    private final CourseStudentDao courseStudentDAO;
    private final CourseDao courseDAO;

    @Autowired
    public StudentService(StudentDao studentDAO, CourseStudentDao courseStudentDAO, CourseDao courseDAO) {
        this.courseDAO = courseDAO;
        this.studentDAO = studentDAO;
        this.courseStudentDAO = courseStudentDAO;
    }

    public ResponseEntity<?> createStudent(final CreateStudentRequestModel createStudentRequestModel) {
        if (createStudentRequestModel.getFirstName() == null || createStudentRequestModel.getLastName() == null ||
                createStudentRequestModel.getMailAddress() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        }
        Student newStudent = Student.builder()
                .firstName(createStudentRequestModel.getFirstName())
                .lastName(createStudentRequestModel.getLastName())
                .mailAddress(createStudentRequestModel.getMailAddress())
                .build();

        int newStudentId = studentDAO.createStudent(newStudent);

        return new ResponseEntity<>(newStudentId, HttpStatus.OK);
    }

    public ResponseEntity<?> getStudentById(final int studentId) {
        if (!studentDAO.existsById(studentId)) {
            return new ResponseEntity<>("Student not found.", HttpStatus.NOT_FOUND);
        }
        Student student = studentDAO.findStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    public ResponseEntity<?> checkEntryCode(String entryCode) {
        if (entryCode == null) {
            return new ResponseEntity<>("No code provided!", HttpStatus.BAD_REQUEST);
        }
        if (!courseStudentDAO.existsByEntryCode(entryCode)) {
            return new ResponseEntity<>("Entry code is not known.", HttpStatus.BAD_REQUEST);
        }

        CourseStudent courseStudent = courseStudentDAO.findByStudentEntryCode(entryCode);
        Course course = courseDAO.findById(courseStudent.getCourseId());
        Student student = studentDAO.findStudentById(courseStudent.getStudentId());

        final CourseStudentResponseModel courseStudentResponseModel = CourseStudentResponseModel
                .builder()
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .courseDescription(course.getCourseDescription())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .build();
        return new ResponseEntity<>(courseStudentResponseModel, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<>(studentDAO.findAllStudents(), HttpStatus.OK);
    }
}
