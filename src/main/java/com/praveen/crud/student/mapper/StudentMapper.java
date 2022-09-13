package com.praveen.crud.student.mapper;

import com.praveen.crud.student.dto.StudentDto;
import com.praveen.crud.student.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface StudentMapper {

StudentMapper STUDENT_MAPPER= Mappers.getMapper(StudentMapper.class);

StudentDto studentDto(Student student);

Student student(StudentDto studentDto);


List<StudentDto> studentDtoList(List<Student> studentList);

List<Student> studentList(List<StudentDto> studentDtoList);

}
