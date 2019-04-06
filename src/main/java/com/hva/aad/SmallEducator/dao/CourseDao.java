package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.Course;
import com.hva.aad.SmallEducator.rowmappers.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean existsByCourseCode(String courseCode) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM course WHERE course_code = ?)",
                new Object[]{courseCode}, Boolean.class);
    }

    public Integer createCourse(Course course) {
        return jdbcTemplate.queryForObject("INSERT INTO course (course_name, course_description, course_code, teacher_id) VALUES (?, ?, ?, ?) RETURNING course_id",
                new Object[]{course.getCourseName(), course.getCourseDescription(), course.getCourseCode(), course.getTeacherId()}, Integer.class);
    }

    public Boolean existsById(int courseId) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM course WHERE course_id = ?)",
                new Object[]{courseId}, Boolean.class);
    }

    public Course findById(int courseId) {
        return jdbcTemplate.queryForObject("SELECT * FROM course WHERE course_id = ?",
                new Object[]{courseId}, new CourseRowMapper());
    }

    public List<Course> findAllByTeacherId(int teacherId) {
        return jdbcTemplate.query("SELECT * FROM course WHERE teacher_id = ?",
                new Object[]{teacherId}, new CourseRowMapper());
    }
}
