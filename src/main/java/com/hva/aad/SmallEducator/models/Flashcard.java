package com.hva.aad.SmallEducator.models;

import lombok.Builder;
import lombok.Value;

/**
 * Model for the flashcard entity
 *
 * @author Tijmen Stor
 */
@Value
@Builder
public class Flashcard {
    private int id;
    private int lessonId;
    private String flashcardName;
    private String flashcardQuestion;
    private String flashcardSolution;
}
