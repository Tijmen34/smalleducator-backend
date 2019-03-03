package com.hva.aad.SmallEducator.models;

import lombok.Value;
import javax.persistence.*;

/**
 * Model for the course entity
 *
 * @author Tijmen Stor & Burak Inan
 */
@Entity
@Value
@Table(name = "Course")
public class CourseModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String courseName;

    @Column(name = "code")
    private String courseCode;

}
