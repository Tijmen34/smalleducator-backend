package com.hva.aad.SmallEducator.models.request;

import lombok.Value;

@Value
public class CreateFlashcardRequestModel {
    private String flashcardName;
    private String flashcardQuestion;
    private String flashcardSolution;
    private int lessonId;
}
