package com.hva.aad.SmallEducator.models.response;

import com.hva.aad.SmallEducator.models.CourseModel;
import lombok.*;

import java.util.Set;

@Data
@Builder
public class TeacherLoginResponseModel {
    private int id;
    private String firstName;
    private String lastName;
    private String mailAddress;
    private Set<CourseModel> courseList;
}
