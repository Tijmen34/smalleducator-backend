package com.hva.aad.SmallEducator.models;

import lombok.Builder;
import lombok.Value;

/**
 * Model for the CourseStudent entity
 *
 * @author Tijmen Stor
 */
@Value
@Builder
public class CourseStudent {
    private int id;
    private int studentId;
    private int courseId;
    private String studentEntryCode;
}
