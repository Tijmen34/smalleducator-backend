package com.hva.aad.SmallEducator.rowmappers;


import com.hva.aad.SmallEducator.models.CourseStudent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStudentRowMapper implements RowMapper<CourseStudent> {
    @Override
    public CourseStudent mapRow(ResultSet resultSet, int i) throws SQLException {
        return CourseStudent.builder()
                .id(resultSet.getInt("course_student_id"))
                .courseId(resultSet.getInt("course_id"))
                .studentId(resultSet.getInt("student_id"))
                .studentEntryCode(resultSet.getString("entry_code"))
                .build();
    }
}
