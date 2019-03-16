package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.TeacherModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<TeacherModel, Integer> {
    Optional<TeacherModel> findByUsername(String username);
    Boolean existsByUsername(String username);
}
