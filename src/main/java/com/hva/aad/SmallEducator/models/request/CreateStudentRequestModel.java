package com.hva.aad.SmallEducator.models.request;

import lombok.Value;

@Value
public class CreateStudentRequestModel {
    private String firstName;
    private String lastName;
    private String mailAddress;
}
