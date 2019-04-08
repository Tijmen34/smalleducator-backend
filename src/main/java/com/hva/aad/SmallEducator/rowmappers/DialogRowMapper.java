package com.hva.aad.SmallEducator.rowmappers;

import com.hva.aad.SmallEducator.models.Dialog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DialogRowMapper implements RowMapper<Dialog> {
    @Override
    public Dialog mapRow(ResultSet resultSet, int i) throws SQLException {
        return Dialog.builder()
                .id(resultSet.getInt("dialog_id"))
                .lessonId(resultSet.getInt("lesson_id"))
                .dialogName(resultSet.getString("dialog_name"))
                .dialogDescription(resultSet.getString("dialog_description"))
                .build();
    }
}
