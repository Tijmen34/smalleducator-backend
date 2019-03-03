package com.hva.aad.SmallEducator.models;

import lombok.Value;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Value
public class StudentCourseIDModel implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private StudentModel studentModel;

    @ManyToOne(cascade = CascadeType.ALL)
    private CourseModel courseModel;
}
