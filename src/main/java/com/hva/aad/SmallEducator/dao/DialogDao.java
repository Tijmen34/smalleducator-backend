package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.Dialog;
import com.hva.aad.SmallEducator.rowmappers.DialogRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DialogDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DialogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer createDialog(Dialog dialog) {
        return jdbcTemplate.queryForObject("INSERT INTO dialog (lesson_id, dialog_name, dialog_description) " +
                "VALUES (?,?,?) RETURNING dialog_id", new Object[]{dialog.getLessonId(), dialog.getDialogName(),
                dialog.getDialogDescription()}, Integer.class);
    }

    public List<Dialog> findAllByLessonId(int lessonId) {
        return jdbcTemplate.query("SELECT * FROM dialog WHERE lesson_id = ?", new Object[]{lessonId}, new DialogRowMapper());
    }

    public Dialog findById(int dialogId) {
        return jdbcTemplate.queryForObject("SELECT * FROM dialog WHERE dialog_id = ?", new Object[]{dialogId}, new DialogRowMapper());
    }

    public Boolean existsById(int dialogId) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM dialog WHERE dialog_id = ?)",
                new Object[]{dialogId}, Boolean.class);
    }

    public void deleteById(int dialogId) {
        jdbcTemplate.update("DELETE FROM dialog WHERE dialog_id = ?", dialogId);
    }
}
