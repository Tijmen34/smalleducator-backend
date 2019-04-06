package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.models.request.CreateTeacherRequestModel;
import com.hva.aad.SmallEducator.models.request.TeacherLoginRequestModel;
import com.hva.aad.SmallEducator.models.response.TeacherLoginResponseModel;
import com.hva.aad.SmallEducator.dao.CourseDao;
import com.hva.aad.SmallEducator.dao.TeacherDao;
import com.hva.aad.SmallEducator.models.Course;
import com.hva.aad.SmallEducator.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for the teacher property to communicate with the repository.
 *
 * @author Burak Inan & Tijmen Stor
 */
@Service
public class TeacherService {

    private final PasswordEncoder passwordEncoder;
    private final CourseDao courseDAO;
    private final TeacherDao teacherDAO;

    @Autowired
    public TeacherService(PasswordEncoder passwordEncoder, CourseDao courseDAO, TeacherDao teacherDAO) {
        this.passwordEncoder = passwordEncoder;
        this.courseDAO = courseDAO;
        this.teacherDAO = teacherDAO;
    }

    public ResponseEntity<?> createTeacher(CreateTeacherRequestModel createTeacherRequestModel) {
        if (createTeacherRequestModel.getFirstName() == null || createTeacherRequestModel.getLastName() == null ||
                createTeacherRequestModel.getMailAddress() == null
                || createTeacherRequestModel.getPassword() == null || createTeacherRequestModel.getUsername() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        }

        if (teacherDAO.existsByUsername(createTeacherRequestModel.getUsername())) {
            return new ResponseEntity<>("Username already exists.", HttpStatus.CONFLICT);
        }
        Teacher teacher = Teacher.builder()
                .firstName(createTeacherRequestModel.getFirstName())
                .lastName(createTeacherRequestModel.getLastName())
                .username(createTeacherRequestModel.getUsername())
                .password(passwordEncoder.encode(createTeacherRequestModel.getPassword()))
                .mailAddress(createTeacherRequestModel.getMailAddress())
                .build();

        int newTeacherId = teacherDAO.createTeacher(teacher);
        return new ResponseEntity<>(newTeacherId, HttpStatus.OK);
    }

    public ResponseEntity<?> loginTeacher(TeacherLoginRequestModel teacherLoginRequestModel) {
        if (teacherLoginRequestModel.getUsername() == null || teacherLoginRequestModel.getPassword() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        }

        if (!teacherDAO.existsByUsername(teacherLoginRequestModel.getUsername())) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
        Teacher teacher = teacherDAO.findByUsername(teacherLoginRequestModel.getUsername());

        if (!passwordEncoder.matches(teacherLoginRequestModel.getPassword(), teacher.getPassword())) {
            return new ResponseEntity<>("Password is incorrect", HttpStatus.UNAUTHORIZED);
        }

        List<Course> courseList = courseDAO.findAllByTeacherId(teacher.getId());

        TeacherLoginResponseModel teacherInformation = TeacherLoginResponseModel.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .mailAddress(teacher.getMailAddress())
                .courseList(courseList)
                .build();
        return new ResponseEntity<>(teacherInformation, HttpStatus.OK);
    }
}
