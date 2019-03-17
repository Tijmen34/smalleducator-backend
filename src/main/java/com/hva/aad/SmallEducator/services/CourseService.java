package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.CourseRepository;
import com.hva.aad.SmallEducator.dao.CourseStudentRepository;
import com.hva.aad.SmallEducator.dao.StudentRepository;
import com.hva.aad.SmallEducator.dao.TeacherRepository;
import com.hva.aad.SmallEducator.models.CourseModel;
import com.hva.aad.SmallEducator.models.TeacherModel;
import com.hva.aad.SmallEducator.models.request.CourseStudentRequestModel;
import com.hva.aad.SmallEducator.models.CourseStudentModel;
import com.hva.aad.SmallEducator.models.StudentModel;
import com.hva.aad.SmallEducator.models.request.CreateCourseRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for the course property to communicate with the repository.
 *
 * @author Burak Inan & Tijmen Stor
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final CourseStudentRepository courseStudentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public CourseService(final CourseRepository courseRepository, final StudentRepository studentRepository, final CourseStudentRepository courseStudentRepository, final TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.courseStudentRepository = courseStudentRepository;
        this.teacherRepository = teacherRepository;
    }

    public ResponseEntity<?> createCourse(final CreateCourseRequestModel courseModel) {
        if (courseModel.getCourseName() == null || courseModel.getCourseCode() == null
                || courseModel.getCourseDescription() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        }

        if (courseRepository.existsByCourseCode(courseModel.getCourseCode())) {
            return new ResponseEntity<>("Course Code already exists.", HttpStatus.CONFLICT);
        }

        final Optional<TeacherModel> teacherModel = teacherRepository.findById(courseModel.getTeacherId());
        if (!teacherModel.isPresent()) {
            return new ResponseEntity<>("Server failed to retrieve information.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        final CourseModel newCourse = new CourseModel(0, courseModel.getCourseName(),
                courseModel.getCourseCode(), courseModel.getCourseDescription(),
                teacherModel.get(), null);
        courseRepository.save(newCourse);
        return new ResponseEntity<>(newCourse.getId(), HttpStatus.OK);


    }

    public ResponseEntity<?> addStudentToCourse(final CourseStudentRequestModel courseStudentRequestModel) {
        if (courseStudentRequestModel.getCourseId() == 0
                || courseStudentRequestModel.getStudentId() == 0) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        }

        if (!studentRepository.existsById(courseStudentRequestModel.getStudentId())) {
            return new ResponseEntity<>("Student not found.", HttpStatus.NOT_FOUND);
        }

        if (!courseRepository.existsById(courseStudentRequestModel.getCourseId())) {
            return new ResponseEntity<>("Course not found.", HttpStatus.NOT_FOUND);
        }

        if (courseStudentRepository.existsByCourse_IdAndStudent_Id(courseStudentRequestModel.getCourseId(),
                courseStudentRequestModel.getStudentId())) {
            return new ResponseEntity<>("Student already belongs to the course", HttpStatus.CONFLICT);
        }

        final Optional<CourseModel> courseModel = courseRepository.findById(courseStudentRequestModel.getCourseId());
        final Optional<StudentModel> studentModel = studentRepository.findById(courseStudentRequestModel.getStudentId());

        if (!courseModel.isPresent()
                || !studentModel.isPresent()) {
            return new ResponseEntity<>("Server failed to retrieve information.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        final CourseStudentModel newCourseStudentModel = new CourseStudentModel(0, studentModel.get(), courseModel.get(), UUID.randomUUID().toString());
        courseStudentRepository.save(newCourseStudentModel);
        return new ResponseEntity<>(newCourseStudentModel.getStudentEntryCode(), HttpStatus.OK);
    }

    public ResponseEntity<?> getCoursesByTeacher(int teacherId) {
        Optional<List<CourseModel>> courseList = courseRepository.findAllByTeacherId(teacherId);
        if (!courseList.isPresent()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }
}
