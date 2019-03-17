package com.hva.aad.SmallEducator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.*;

/**
 * Model for the CourseStudent entity
 *
 * @author Tijmen Stor & Burak Inan
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"Course_has_Student\"")
public class CourseStudentModel {

    @Id
    @GeneratedValue
    @Column(name = "course_student_id")
    @JsonIgnore
    private int id;

    @ManyToOne(
            targetEntity = StudentModel.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @JsonIgnoreProperties("courses")
    @JsonManagedReference
    private StudentModel student;

    @ManyToOne(
            targetEntity = CourseModel.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @JsonIgnoreProperties("students")
    @JsonManagedReference
    private CourseModel course;

    @Column(name = "entry_code")
    private String studentEntryCode;

}
