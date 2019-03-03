package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.StudentDao;
import com.hva.aad.SmallEducator.models.StudentModel;
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

    private final StudentDao studentDao;

    public StudentService (final StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public ResponseEntity<?> createStudent(final StudentModel studentModel) {
        return new ResponseEntity<>("Successfully created student.", HttpStatus.OK);
    }
}
