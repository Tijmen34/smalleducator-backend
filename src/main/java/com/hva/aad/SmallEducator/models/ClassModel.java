package com.hva.aad.SmallEducator.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ClassModel {
    @Id
    @GeneratedValue
    private Long id;

    private String classCode;

    private String classCourse;

}
