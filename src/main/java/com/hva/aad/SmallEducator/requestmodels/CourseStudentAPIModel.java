package com.hva.aad.SmallEducator.requestmodels;

import lombok.Value;

/**
 * Model for the CourseStudent API requestbody.
 *
 * @author Tijmen Stor & Burak Inan
 */
@Value
public class CourseStudentAPIModel {
    private int courseId;
    private int studentId;
}
