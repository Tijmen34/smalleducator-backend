package com.hva.aad.SmallEducator.models;

import lombok.Value;

import javax.persistence.*;

/**
 * Model for the CourseStudent entity
 *
 * @author Tijmen Stor & Burak Inan
 */
@Entity
@Value
@Table(name = "\"Course_has_Student\"")
public class CourseStudentModel {

    @Id
    @GeneratedValue
    @Column(name = "course_student_id")
    private int id;

    @ManyToOne(
            targetEntity = StudentModel.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private StudentModel student;

    @ManyToOne(
            targetEntity = CourseModel.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private CourseModel course;

    @Column(name = "entry_code")
    private String studentEntryCode;

}
