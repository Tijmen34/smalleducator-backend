package com.hva.aad.SmallEducator.models;

import lombok.Value;
import javax.persistence.*;

/**
 * Model for the student entity
 *
 * @author Tijmen Stor & Burak Inan
 */
@Entity
@Value
@Table(name = "Student")
public class StudentModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="mail_address")
    private String mailAddress;
}
