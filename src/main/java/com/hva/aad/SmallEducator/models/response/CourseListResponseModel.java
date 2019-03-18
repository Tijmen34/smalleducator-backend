package com.hva.aad.SmallEducator.models.response;

import com.hva.aad.SmallEducator.models.StudentModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CourseListResponseModel {
    private int id;
    private String courseName;
    private String courseCode;
    private String courseDescription;
    private List<StudentModel> students;
}
