package com.hva.aad.SmallEducator.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseStudentResponseModel {
    private String firstName;
    private String lastName;
    private String courseName;
    private String courseCode;
    private String courseDescription;
}
