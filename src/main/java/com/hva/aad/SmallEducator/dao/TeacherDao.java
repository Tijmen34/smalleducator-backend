package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.TeacherModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao extends CrudRepository<TeacherModel, Integer> {
}
