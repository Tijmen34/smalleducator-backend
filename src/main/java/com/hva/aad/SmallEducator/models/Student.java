package com.hva.aad.SmallEducator.models;

import lombok.*;


/**
 * Model for the student entity
 *
 * @author Tijmen Stor
 */
@Value
@Builder
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String mailAddress;
}