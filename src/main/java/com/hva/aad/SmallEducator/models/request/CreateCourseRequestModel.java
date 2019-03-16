package com.hva.aad.SmallEducator.models.request;

import lombok.Value;

@Value
public class CreateCourseRequestModel {
    private String courseName;
    private String courseCode;
    private String courseDescription;
    private int teacherId;
}
