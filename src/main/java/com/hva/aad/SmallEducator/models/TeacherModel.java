package com.hva.aad.SmallEducator.models;

import lombok.Value;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Model for the teacher entity
 *
 * @author Tijmen Stor & Burak Inan
 */
@Entity
@Value
@Table(name = "\"Teacher\"")
public class TeacherModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "teacher_id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="mail_address")
    private String mailAddress;


}
