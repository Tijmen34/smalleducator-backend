package com.hva.aad.SmallEducator.models.request;

import lombok.Value;

@Value
public class CreateTeacherRequestModel {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String mailAddress;
}
