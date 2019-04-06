package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.CourseStudent;
import com.hva.aad.SmallEducator.rowmappers.CourseStudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseStudentDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseStudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer createCourseStudent(CourseStudent newCourseStudent) {
        return jdbcTemplate.queryForObject("INSERT INTO course_has_student (course_id, student_id, entry_code) " +
                        "VALUES (?, ?, ?) RETURNING course_student_id",
                new Object[]{newCourseStudent.getCourseId(), newCourseStudent.getStudentId(), newCourseStudent.getStudentEntryCode()}, Integer.class);
    }

    public Boolean existsByEntryCode (String entryCode) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM course_has_student WHERE entry_code = ?)",
                new Object[]{entryCode}, Boolean.class);
    }

    public CourseStudent findByStudentEntryCode(String entryCode) {
        return jdbcTemplate.queryForObject("SELECT * FROM course_has_student WHERE entry_code = ?",
                new Object[]{entryCode}, new CourseStudentRowMapper());
    }

    public List<CourseStudent> findAllByStudentId (int studentId) {
        return jdbcTemplate.query("SELECT * FROM course_has_student WHERE student_id = ?",
                new Object[]{studentId}, new CourseStudentRowMapper());
    }

    public Boolean existsByCourseIdAndStudentId (int courseId, int studentId) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM course_has_student WHERE course_id = ? AND student_id = ?)",
                new Object[]{courseId, studentId}, Boolean.class);
    }

}
