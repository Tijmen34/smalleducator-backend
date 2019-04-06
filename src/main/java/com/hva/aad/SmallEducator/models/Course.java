package com.hva.aad.SmallEducator.models;

import lombok.*;

/**
 * Model for the course entity
 *
 * @author Tijmen Stor
 */
@Value
@Builder
public class Course {
    private int id;
    private int teacherId;
    private String courseName;
    private String courseCode;
    private String courseDescription;
}