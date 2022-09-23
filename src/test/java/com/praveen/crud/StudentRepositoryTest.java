package com.praveen.crud.student.repository;

import com.praveen.crud.student.dto.StudentDto;
import com.praveen.crud.student.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void getAgeByFirstNameTest() {
        Student student = studentRepository.getAgeByFirstName("praveen"); //Rahul is error

        System.out.println("Student =" + student);
    }

    @Test
    public void findStudentByAgeTest() {
        List<Student> students = studentRepository.findStudentByAge(23);

        System.out.println("Student =" + students);

    }

    @Test
    public void findByStudentStartingLetterTest() {
        List<Student> students = studentRepository.findByStudentStartingLetter("Pr%"); // % is compulsary
        System.out.println("student=" + students);
    }

    @Test
    public void findByAgeGreaterThanTest() {
        List<Student> students = studentRepository.findByAgeGreaterThan(22);
        System.out.println("Students =" + students);
    }

    @Test
    public void findByAgeLessThanTest() {
        List<Student> students = studentRepository.findByAgeLessThan(22);
        System.out.println("Students =" + students);
    }

    @Test
    public void findByAgeOrderNameDescTest() {
        List<Student> students = studentRepository.findByAgeOrderNameDesc("Abishek");
        System.out.println("Students =" + students);
    }

    @Test
    public void getStudentsByAgeNativeTest() {
        Student student = studentRepository.getStudentsByAgeNative(28);
        System.out.println("Students =" + student);

    }

    @Test
    public void getStudentsByAgeNativeTwoTest() {
        Student student = studentRepository.getStudentsByAgeNativeTwo(28);
        System.out.println("Students =" + student);
    }

    @Test
    public void getStudentsByAgeNativeNamedParamTest() {
        Student student = studentRepository.getStudentsByAgeNativeNamedParam(28);
        System.out.println("Students =" + student);
    }

    @Test
    public void updateAgeByNameTest(){
        studentRepository.updateAgeByName(30,"Abishek");
    }

}