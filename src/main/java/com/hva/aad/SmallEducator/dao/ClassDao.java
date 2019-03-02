package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.ClassModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDao extends CrudRepository<ClassModel, Long> {
}
