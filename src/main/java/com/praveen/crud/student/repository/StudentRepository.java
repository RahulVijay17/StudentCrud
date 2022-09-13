package com.praveen.crud.student.repository;

import com.praveen.crud.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    Optional<Student> findByIdAndName(Integer id, String name);
}
