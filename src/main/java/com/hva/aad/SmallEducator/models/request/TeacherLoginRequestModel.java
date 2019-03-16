package com.hva.aad.SmallEducator.models.request;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TeacherLoginRequestModel {
    private String username;
    private String password;
}
