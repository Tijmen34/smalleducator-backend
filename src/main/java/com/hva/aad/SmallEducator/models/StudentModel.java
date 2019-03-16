package com.hva.aad.SmallEducator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Model for the student entity
 *
 * @author Tijmen Stor & Burak Inan
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"Student\"")
public class StudentModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="mail_address")
    private String mailAddress;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<CourseStudentModel> courses = new HashSet<>();
}
