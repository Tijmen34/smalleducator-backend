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
        this.teacherDao= teacherDao;
    }

    public ResponseEntity<?> createTeacher(TeacherModel teacherModel) {
        return new ResponseEntity<>("Successfully created teacher.", HttpStatus.OK);
    }
}
