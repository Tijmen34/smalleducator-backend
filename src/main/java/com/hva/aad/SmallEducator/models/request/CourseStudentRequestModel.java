package com.hva.aad.SmallEducator.models.request;

import lombok.Value;

/**
 * Model for the CourseStudent API requestbody.
 *
 * @author Tijmen Stor & Burak Inan
 */
@Value
public class CourseStudentRequestModel {
    private int courseId;
    private int studentId;
}
