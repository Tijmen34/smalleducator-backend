package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.CourseStudentRepository;
import com.hva.aad.SmallEducator.dao.StudentRepository;
import com.hva.aad.SmallEducator.models.CourseStudentModel;
import com.hva.aad.SmallEducator.models.StudentModel;
import com.hva.aad.SmallEducator.models.request.CreateStudentRequestModel;
import com.hva.aad.SmallEducator.models.response.CourseStudentResponseModel;
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

    private final StudentRepository studentRepository;
    private final CourseStudentRepository courseStudentRepository;

    @Autowired
    public StudentService(final StudentRepository studentRepository, final CourseStudentRepository courseStudentRepository) {
        this.studentRepository = studentRepository;
        this.courseStudentRepository = courseStudentRepository;
    }

    public ResponseEntity<?> createStudent(final CreateStudentRequestModel createStudentRequestModel) {
        if (createStudentRequestModel.getFirstName() == null || createStudentRequestModel.getLastName() == null ||
                createStudentRequestModel.getMailAddress() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        }
        StudentModel studentModel = StudentModel.builder()
                .firstName(createStudentRequestModel.getFirstName())
                .lastName(createStudentRequestModel.getLastName())
                .mailAddress(createStudentRequestModel.getMailAddress())
                .build();

        studentRepository.save(studentModel);
        return new ResponseEntity<>(studentModel.getId(), HttpStatus.OK);
    }

    public ResponseEntity<?> checkEntryCode(String entryCode) {
        if (entryCode == null) {
            return new ResponseEntity<>("No code provided!", HttpStatus.BAD_REQUEST);
        }

        final Optional<CourseStudentModel> courseStudentModel = courseStudentRepository.findByStudentEntryCode(entryCode);
        if (!courseStudentModel.isPresent()) {
            return new ResponseEntity<>("Server failed to retrieve information", HttpStatus.INTERNAL_SERVER_ERROR);
        }

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

    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }
}
