package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.Lesson;
import com.hva.aad.SmallEducator.rowmappers.LessonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Tijmen Stor
 */
@Repository
public class LessonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LessonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer createLesson(Lesson lesson) {
        return jdbcTemplate.queryForObject("INSERT INTO lesson (course_id, lesson_name, lesson_description) " +
                "VALUES (?,?,?) RETURNING lesson_id", new Object[]{lesson.getCourseId(), lesson.getLessonName(),
                lesson.getLessonDescription()}, Integer.class);
    }

    public Boolean existsById(int lessonId) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM lesson WHERE lesson_id = ?)",
                new Object[]{lessonId}, Boolean.class);
    }

    public Lesson findById(int lessonId) {
        return jdbcTemplate.queryForObject("SELECT * FROM lesson WHERE lesson_id = ?",
                new Object[]{lessonId}, new LessonRowMapper());
    }

    public void deleteByLessonId(int lessonId) {
        jdbcTemplate.update("DELETE FROM dialog WHERE lesson_id = ?",lessonId);
        jdbcTemplate.update("DELETE FROM flashcard WHERE lesson_id = ?", lessonId);
        jdbcTemplate.update("DELETE FROM lesson WHERE lesson_id = ?", lessonId);
    }
}
