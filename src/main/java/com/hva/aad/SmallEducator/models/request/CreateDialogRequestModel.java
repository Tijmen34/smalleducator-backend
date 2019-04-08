package com.hva.aad.SmallEducator.models.request;

import lombok.Value;

@Value
public class CreateDialogRequestModel {
    private String dialogName;
    private String dialogDescription;
    private int lessonId;
}
