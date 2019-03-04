package com.hva.aad.SmallEducator.models;

import lombok.Value;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Model for the course entity
 *
 * @author Tijmen Stor & Burak Inan
 */
@Entity
@Value
@Table(name = "\"Course\"")
public class CourseModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private int id;

    @Column(name = "name")
    private String courseName;

    @Column(name = "code")
    private String courseCode;

    @Column(name = "description")
    private String courseDescription;

    @OneToMany(mappedBy = "course")
    private Set<CourseStudentModel> students = new HashSet<>();

}
