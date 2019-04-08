package com.hva.aad.SmallEducator.models;

import lombok.Builder;
import lombok.Value;

/**
 * Model for the lesson entity
 *
 * @author Tijmen Stor
 */
@Value
@Builder
public class Lesson {
    private int id;
    private int courseId;
    private String lessonName;
    private String lessonDescription;
}
