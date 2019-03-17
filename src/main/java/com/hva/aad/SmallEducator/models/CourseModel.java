package com.hva.aad.SmallEducator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Model for the course entity
 *
 * @author Tijmen Stor & Burak Inan
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"Course\"")
public class CourseModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private int id;

    @Column(name = "name")
    private String courseName;

    @Column(name = "code")
    private String courseCode;

    @Column(name = "description")
    private String courseDescription;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnoreProperties("courseList")
    private TeacherModel teacher;

    @OneToMany(mappedBy = "course")
    @JsonIgnoreProperties({"id", "course", "studentEntryCode"})
    @JsonBackReference
    private Set<CourseStudentModel> students = new HashSet<>();

}
