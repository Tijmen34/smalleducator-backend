package com.hva.aad.SmallEducator.models;

import lombok.Builder;
import lombok.Value;

/**
 * Model for the teacher entity
 *
 * @author Tijmen Stor
 */
@Builder
@Value
public class Teacher {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String mailAddress;
}
