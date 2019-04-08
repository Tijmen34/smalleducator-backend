package com.hva.aad.SmallEducator.models.response;

import com.hva.aad.SmallEducator.models.Dialog;
import com.hva.aad.SmallEducator.models.Flashcard;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class LessonResponseModel {
    private int id;
    private int courseId;
    private String lessonName;
    private String lessonDescription;
    private List<Flashcard> flashcardList;
    private List<Dialog> dialogList;
}
