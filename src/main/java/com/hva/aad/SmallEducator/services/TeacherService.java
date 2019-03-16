package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.TeacherRepository;
import com.hva.aad.SmallEducator.models.TeacherModel;
import com.hva.aad.SmallEducator.models.request.TeacherLoginRequestModel;
import com.hva.aad.SmallEducator.models.response.TeacherLoginResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for the teacher property to communicate with the repository.
 *
 * @author Burak Inan & Tijmen Stor
 */
@Service
public class TeacherService {

    private final PasswordEncoder passwordEncoder;

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(final TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> createTeacher(TeacherModel teacherModel) {
        if (teacherModel.getFirstName() == null || teacherModel.getLastName() == null ||
                teacherModel.getMailAddress() == null || teacherModel.getPassword() == null ||
                teacherModel.getUsername() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        } else {
            if (teacherRepository.existsByUsername(teacherModel.getUsername())) {
                return new ResponseEntity<>("Username already exists.", HttpStatus.CONFLICT);
            }
            teacherModel.setPassword(passwordEncoder.encode(teacherModel.getPassword()));
            teacherRepository.save(teacherModel);
            return new ResponseEntity<>(teacherModel.getId(), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> loginTeacher(TeacherLoginRequestModel teacherLoginRequestModel) {
        if (teacherLoginRequestModel.getUsername() == null || teacherLoginRequestModel.getPassword() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        }
        Optional<TeacherModel> teacherModel = teacherRepository.findByUsername(teacherLoginRequestModel.getUsername());
        if (!teacherModel.isPresent()){
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
        if (!passwordEncoder.matches(teacherLoginRequestModel.getPassword(), teacherModel.get().getPassword())) {
            return new ResponseEntity<>("Password is incorrect", HttpStatus.UNAUTHORIZED);
        }
        TeacherLoginResponseModel teacherInformation = TeacherLoginResponseModel.builder()
                .id(teacherModel.get().getId())
                .firstName(teacherModel.get().getFirstName())
                .lastName(teacherModel.get().getLastName())
                .mailAddress(teacherModel.get().getMailAddress())
                .courseList(teacherModel.get().getCourseList())
                .build();
        return new ResponseEntity<>(teacherInformation, HttpStatus.OK);
    }
}
