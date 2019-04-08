package com.hva.aad.SmallEducator.rowmappers;

import com.hva.aad.SmallEducator.models.Lesson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonRowMapper implements RowMapper<Lesson> {
    @Override
    public Lesson mapRow(ResultSet resultSet, int i) throws SQLException {
        return Lesson.builder()
                .id(resultSet.getInt("lesson_id"))
                .courseId(resultSet.getInt("course_id"))
                .lessonName(resultSet.getString("lesson_name"))
                .lessonDescription(resultSet.getString("lesson_description"))
                .build();
    }
}
