package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.StudentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<StudentModel, Long> {
}

