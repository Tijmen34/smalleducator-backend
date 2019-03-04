package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.CourseModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends CrudRepository<CourseModel, Long> {
    Boolean existsByCourseCode(String courseCode);
}
