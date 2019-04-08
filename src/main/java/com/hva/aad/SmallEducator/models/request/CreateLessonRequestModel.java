package com.hva.aad.SmallEducator.models.request;

import lombok.Value;

@Value
public class CreateLessonRequestModel {
    private String lessonName;
    private String lessonDescription;
    private int courseId;
}
