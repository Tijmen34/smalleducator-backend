package com.hva.aad.SmallEducator.dao;

import com.hva.aad.SmallEducator.models.Flashcard;
import com.hva.aad.SmallEducator.rowmappers.FlashcardRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlashcardDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FlashcardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer createFlashcard(Flashcard flashcard) {
        return jdbcTemplate.queryForObject("INSERT INTO flashcard (lesson_id, flashcard_name, flashcard_question, flashcard_solution) " +
                "VALUES (?,?,?,?) RETURNING flashcard_id", new Object[]{flashcard.getLessonId(), flashcard.getFlashcardName(),
                flashcard.getFlashcardQuestion(), flashcard.getFlashcardSolution()}, Integer.class);
    }

    public List<Flashcard> findAllByLessonId(int lessonId) {
        return jdbcTemplate.query("SELECT * FROM flashcard WHERE lesson_id = ?", new Object[]{lessonId}, new FlashcardRowMapper());
    }

    public Flashcard findById(int flashcardId) {
        return jdbcTemplate.queryForObject("SELECT * FROM flashcard WHERE flashcard_id = ?", new Object[]{flashcardId}, new FlashcardRowMapper());
    }

    public Boolean existsById(int flashcardId) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM flashcard WHERE flashcard_id= ?)",
                new Object[]{flashcardId}, Boolean.class);
    }

    public void deleteById(int flashcardId) {
        jdbcTemplate.update("DELETE FROM flashcard WHERE flashcard_id = ?", flashcardId);
    }
}
