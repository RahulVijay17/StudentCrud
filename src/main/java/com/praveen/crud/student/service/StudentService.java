package com.praveen.crud.student.service;

import com.praveen.crud.student.dto.StudentDto;
import com.praveen.crud.student.model.Student;

import java.util.List;

public interface StudentService {
    StudentDto addStudent( StudentDto studentDto);

    List<StudentDto> getAllStudent();

    StudentDto delStudentById(Integer id);

    StudentDto getStudentById(StudentDto studentDto);

    StudentDto updateStudent(StudentDto studentDto);

/*
    String studentFiles(Integer id, MultipartFile file);
*/
}
