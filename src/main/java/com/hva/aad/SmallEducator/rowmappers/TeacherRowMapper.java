package com.hva.aad.SmallEducator.rowmappers;

import com.hva.aad.SmallEducator.models.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRowMapper implements RowMapper<Teacher> {

    @Override
    public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
        return Teacher.builder()
                .id(resultSet.getInt("teacher_id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .mailAddress(resultSet.getString("mail_address"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .build();
    }
}
