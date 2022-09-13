package com.praveen.crud.student.model;

/*
import com.praveen.crud.student.helper.FileUploadHelper;
*/

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "student_photo")
    private String photo;

 /*   @Embedded
    public FileUploadHelper fileUploadHelper;*/
}
