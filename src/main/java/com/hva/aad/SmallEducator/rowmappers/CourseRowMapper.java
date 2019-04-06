package com.hva.aad.SmallEducator.rowmappers;

import com.hva.aad.SmallEducator.models.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        return Course.builder()
                .id(resultSet.getInt("course_id"))
                .courseName(resultSet.getString("course_name"))
                .courseDescription(resultSet.getString("course_description"))
                .courseCode(resultSet.getString("course_code"))
                .teacherId(resultSet.getInt("teacher_id"))
                .build();
    }
}
