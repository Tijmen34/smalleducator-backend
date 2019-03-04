package com.hva.aad.SmallEducator.requestmodels;

import lombok.Value;

@Value
public class CreateCourseModel {
    private String courseName;
    private String courseCode;
    private String courseDescription;
    private int teacherId;
}
