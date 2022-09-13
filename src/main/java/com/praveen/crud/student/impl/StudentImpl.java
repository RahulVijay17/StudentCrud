package com.praveen.crud.student.impl;

import com.praveen.crud.student.dto.StudentDto;
import com.praveen.crud.student.mapper.StudentMapper;
import com.praveen.crud.student.model.Student;
import com.praveen.crud.student.repository.StudentRepository;
import com.praveen.crud.student.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentImpl.class);

    @Autowired
    StudentRepository studentRepository;

    public final String UPLOAD_DIR="C:\\Users\\HP\\Downloads\\StudentCrud\\src\\main\\resources\\static";

    @Override
    public StudentDto addStudent( StudentDto studentDto) {
        logger.info("ADD Students Impl");

        /*Student student = new Student();
        student.setAge(studentDto.getAge());
        student.setName(studentDto.getName());
        Student student1 =  studentRepository.save(student);
        studentDto.setId(student1.getId());*/
        try{
            Student student = StudentMapper.STUDENT_MAPPER.student(studentDto);
            Student student1 =  studentRepository.save(student);

            return StudentMapper.STUDENT_MAPPER.studentDto(student1);
        }
        catch(Exception e){
            logger.error("Not Created"+e);
            return studentDto;
        }


    }

    @Override
    public List<StudentDto> getAllStudent() {
        logger.info("GetAll Students Impl");

        List<StudentDto> studentDto = new ArrayList<>();
        try {
            List<Student> studentList=studentRepository.findAll();
            return StudentMapper.STUDENT_MAPPER.studentDtoList(studentList);
        } catch (Exception e) {
            logger.error("No Id" + e);
            return studentDto;
        }
    }

    @Override
    public StudentDto delStudentById(Integer id) {
        logger.info("Delete Method");
        StudentDto studentDto= new StudentDto();
        try{

            studentRepository.deleteById(id);
            return studentDto;
        }
        catch (Exception e){
            logger.error("No id Present");
            return studentDto;
        }
    }


    @Override
    public StudentDto getStudentById(StudentDto studentDto) {
        logger.info("Getting single id");
        StudentDto studentDto1 = new StudentDto();
        try {
            if (studentDto.getId() != null && studentDto.getName().isEmpty() && studentDto.getAge() == null) {
                Optional<Student> student = studentRepository.findById(studentDto.getId());
                if (student.isPresent()) return StudentMapper.STUDENT_MAPPER.studentDto(student.get());
                else {
                    return studentDto;
                }
            } else if (studentDto.getId() != null && studentDto.getName() != null) {
                Optional<Student> student = studentRepository.findByIdAndName(studentDto.getId(), studentDto.getName());
                if (student.isPresent()) return StudentMapper.STUDENT_MAPPER.studentDto(student.get());
                else {
                    return studentDto;
                }
            } else {
                return studentDto;
            }
        } catch (Exception e) {
            logger.error("update not changed");
            return studentDto;

        }
    }
    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        logger.info("update single id");

        try {
            Student student = new Student();
            student.setId(studentDto.getId());
            Student studentDao = StudentMapper.STUDENT_MAPPER.student(studentDto);
            Student students = studentRepository.save(studentDao);
            return StudentMapper.STUDENT_MAPPER.studentDto(student);
        } catch (Exception e) {
            logger.error("update not changed");
            return studentDto;
        }
    }


}
