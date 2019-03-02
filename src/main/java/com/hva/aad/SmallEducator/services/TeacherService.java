package com.hva.aad.SmallEducator.services;

import com.hva.aad.SmallEducator.dao.ClassDao;
import com.hva.aad.SmallEducator.models.ClassModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final ClassDao classDao;

    @Autowired
    public TeacherService (final ClassDao classDao) {
        this.classDao = classDao;
    }

    public ResponseEntity<?> createClass(ClassModel classModel) {
        classDao.save(classModel);
        return new ResponseEntity<>("Successfully created class.", HttpStatus.OK);
    }
}
