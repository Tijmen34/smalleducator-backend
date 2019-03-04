package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.TeacherDao;
import com.hva.aad.SmallEducator.models.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service for the teacher property to communicate with the repository.
 * TODO: Create the logic for routes.
 *
 * @author Burak Inan & Tijmen Stor
 */
@Service
public class TeacherService {

    private final TeacherDao teacherDao;

    @Autowired
    public TeacherService(final TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public ResponseEntity<?> createTeacher(TeacherModel teacherModel) {
        if (teacherModel.getFirstName() == null ||
                teacherModel.getLastName() == null ||
                teacherModel.getMailAddress() == null) {
            return new ResponseEntity<>("Not all fields are provided", HttpStatus.BAD_REQUEST);
        } else {
            teacherDao.save(teacherModel);
            return new ResponseEntity<>(teacherModel.getId(), HttpStatus.OK);
        }
    }
}
