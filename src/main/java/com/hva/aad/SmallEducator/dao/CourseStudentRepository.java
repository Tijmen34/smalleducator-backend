package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.CourseStudentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseStudentRepository extends CrudRepository<CourseStudentModel, Integer> {
    Boolean existsByCourse_IdAndStudent_Id(int course_id, int student_id);
    Optional<CourseStudentModel> findByStudentEntryCode(String studentEntryCode);
}
