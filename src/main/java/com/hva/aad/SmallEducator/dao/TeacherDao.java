package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.Teacher;
import com.hva.aad.SmallEducator.rowmappers.TeacherRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer createTeacher(Teacher teacher) {
        return jdbcTemplate.queryForObject("INSERT INTO teacher (first_name, last_name, mail_address, username, password)" +
                        " VALUES (?, ?, ?, ?, ?) RETURNING teacher_id",
                new Object[]{teacher.getFirstName(), teacher.getLastName(), teacher.getMailAddress(),
                        teacher.getUsername(), teacher.getPassword()}, Integer.class);
    }

    public Boolean existsByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM teacher WHERE username = ?)",
                new Object[]{username}, Boolean.class);
    }

    public Boolean existsById(int teacherId) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM teacher WHERE teacher_id = ?)",
                new Object[]{teacherId}, Boolean.class);
    }

    public Teacher findById(int teacherId) {
        return jdbcTemplate.queryForObject("SELECT * FROM teacher WHERE teacher_id = ?",
                new Object[]{teacherId}, new TeacherRowMapper());
    }

    public Teacher findByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM teacher WHERE username = ?",
                new Object[]{username}, new TeacherRowMapper());
    }
}
