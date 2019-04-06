package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.Student;
import com.hva.aad.SmallEducator.rowmappers.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer createStudent(Student student) {
        return jdbcTemplate.queryForObject("INSERT INTO student (first_name, last_name, mail_address) VALUES (?, ?, ?) RETURNING student_id",
                new Object[]{student.getFirstName(), student.getLastName(), student.getMailAddress()}, Integer.class);
    }

    public Student findStudentById(int studentId) {
        return jdbcTemplate.queryForObject("SELECT * FROM student WHERE student_id = ?",
                new Object[]{studentId}, new StudentRowMapper());
    }

    public Boolean existsById(int studentId) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM student WHERE student_id = ?)",
                new Object[]{studentId}, Boolean.class);
    }

    public List<Student> findAllStudents() {
        return jdbcTemplate.query("SELECT * FROM student",
                new Object[]{}, new StudentRowMapper());
    }

}
