package com.hva.aad.SmallEducator.models;

import lombok.Builder;
import lombok.Value;

/**
 * Model for the dialog entity
 *
 * @author Tijmen Stor
 */
@Value
@Builder
public class Dialog {
    private int id;
    private int lessonId;
    private String dialogName;
    private String dialogDescription;
}
