package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.CourseModel;
import com.hva.aad.SmallEducator.models.CourseStudentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CourseRepository extends CrudRepository<CourseModel, Integer> {
    Boolean existsByCourseCode(String courseCode);
    Boolean existsById(int id);
    Optional<List<CourseModel>> findAllByTeacherId(int teacher_id);
    Optional<List<CourseModel>> findAllByStudents(Set<CourseStudentModel> students);
}
