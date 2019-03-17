package com.hva.aad.SmallEducator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Model for the teacher entity
 *
 * @author Tijmen Stor & Burak Inan
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"Teacher\"")
public class TeacherModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "teacher_id")
    private int id;

    @Column(name="username", unique = true)
    @JsonIgnore
    private String username;

    @Column(name="password")
    @JsonIgnore
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="mail_address")
    private String mailAddress;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnoreProperties("teacher")
    private Set<CourseModel> courseList = new HashSet<>();

}
