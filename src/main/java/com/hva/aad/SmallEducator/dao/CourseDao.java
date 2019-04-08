package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.Course;
import com.hva.aad.SmallEducator.models.Lesson;
import com.hva.aad.SmallEducator.rowmappers.CourseRowMapper;
import com.hva.aad.SmallEducator.rowmappers.LessonRowMapper;
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

    public void deleteByCourseId(int courseId) {
        List<Lesson> lessonList = jdbcTemplate.query("SELECT * FROM lesson WHERE course_id = ?",
                new Object[]{courseId}, new LessonRowMapper());

        for (Lesson lesson : lessonList) {
            jdbcTemplate.update("DELETE FROM dialog WHERE lesson_id = ?", lesson.getId());
            jdbcTemplate.update("DELETE FROM flashcard WHERE lesson_id = ?", lesson.getId());
            jdbcTemplate.update("DELETE FROM lesson WHERE lesson_id = ?", lesson.getId());
        }
        jdbcTemplate.update("DELETE FROM course_has_student WHERE course_id = ?", courseId);
        jdbcTemplate.update("DELETE FROM course WHERE course_id = ?", courseId);
    }
}
