package com.hva.aad.SmallEducator.rowmappers;

import com.hva.aad.SmallEducator.models.Flashcard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlashcardRowMapper implements RowMapper<Flashcard> {
    @Override
    public Flashcard mapRow(ResultSet resultSet, int i) throws SQLException {
        return Flashcard.builder()
                .id(resultSet.getInt("flashcard_id"))
                .lessonId(resultSet.getInt("lesson_id"))
                .flashcardName(resultSet.getString("flashcard_name"))
                .flashcardQuestion(resultSet.getString("flashcard_question"))
                .flashcardSolution(resultSet.getString("flashcard_solution"))
                .build();
    }
}
