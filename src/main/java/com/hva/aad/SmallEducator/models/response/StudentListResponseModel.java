package com.hva.aad.SmallEducator.models.response;

import com.hva.aad.SmallEducator.models.CourseModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class StudentListResponseModel {
    private int id;
    private String firstName;
    private String lastName;
    private String mailAddress;
    private List<CourseModel> courseList;
}
