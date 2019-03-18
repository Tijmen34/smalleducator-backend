package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.CourseRepository;
import com.hva.aad.SmallEducator.dao.CourseStudentRepository;
import com.hva.aad.SmallEducator.dao.StudentRepository;
import com.hva.aad.SmallEducator.models.CourseModel;
import com.hva.aad.SmallEducator.models.CourseStudentModel;
import com.hva.aad.SmallEducator.models.StudentModel;
import com.hva.aad.SmallEducator.models.request.CreateStudentRequestModel;
import com.hva.aad.SmallEducator.models.response.CourseStudentResponseModel;
import com.hva.aad.SmallEducator.models.response.StudentListResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(final StudentRepository studentRepository, final CourseStudentRepository courseStudentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseStudentRepository = courseStudentRepository;
        this.courseRepository = courseRepository;
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

    public ResponseEntity<?> getStudentById(final int studentId) {
        Optional<StudentModel> studentModel = studentRepository.findById(studentId);
        if (!studentModel.isPresent()) {
            return new ResponseEntity<>("Student not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentModel, HttpStatus.OK);
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
        List<StudentListResponseModel> newStudentList = new ArrayList<>();
        Iterable<StudentModel> studentList = studentRepository.findAll();
        for (StudentModel studentModel: studentList) {
            Optional<List<CourseStudentModel>> courseStudentModelList = courseStudentRepository.findAllByStudent_Id(studentModel.getId());
            if (!courseStudentModelList.isPresent()) {
                StudentListResponseModel student = StudentListResponseModel.builder()
                        .id(studentModel.getId())
                        .firstName(studentModel.getFirstName())
                        .lastName(studentModel.getLastName())
                        .mailAddress(studentModel.getMailAddress())
                        .courseList(new ArrayList<>())
                        .build();
                newStudentList.add(student);
            } else {
                List<CourseModel> courseList = new ArrayList<>();
                for (CourseStudentModel courseStudentModel: courseStudentModelList.get()) {
                    courseList.add(courseStudentModel.getCourse());
                }
                StudentListResponseModel student = StudentListResponseModel.builder()
                        .id(studentModel.getId())
                        .firstName(studentModel.getFirstName())
                        .lastName(studentModel.getLastName())
                        .mailAddress(studentModel.getMailAddress())
                        .courseList(courseList)
                        .build();
                newStudentList.add(student);
            }
        }
        return new ResponseEntity<>(newStudentList, HttpStatus.OK);
    }
}
