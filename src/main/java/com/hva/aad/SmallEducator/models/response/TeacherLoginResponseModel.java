package com.hva.aad.SmallEducator.models.response;

import com.hva.aad.SmallEducator.models.Course;
import lombok.*;

import java.util.List;

@Data
@Builder
public class TeacherLoginResponseModel {
    private int id;
    private String firstName;
    private String lastName;
    private String mailAddress;
    private List<Course> courseList;
}
