package com.hva.aad.SmallEducator.rowmappers;

import com.hva.aad.SmallEducator.models.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        return Student.builder()
                .id(resultSet.getInt("student_id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .mailAddress(resultSet.getString("mail_address"))
                .build();
    }
}
