package com.praveen.crud.student.repository;

import com.praveen.crud.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByIdAndName(Integer id, String name);

    //Fetching data queries
    @Query("select s from Student s where s.name=?1")
    Student getAgeByFirstName(String name);


    @Query("select s from Student  s where s.age=?1")
    List<Student> findStudentByAge(Integer age);

    @Query("select s from Student  s where s.name like ?1")
    List<Student> findByStudentStartingLetter(String name);

    @Query("select s from Student s where s.age > ?1 ")
    List<Student> findByAgeGreaterThan(Integer age);

    @Query("select s from Student s where s.age < ?1")
    List<Student> findByAgeLessThan(Integer age);

    @Query("select s from Student s where s.name=?1 order by s.age desc")
    List<Student> findByAgeOrderNameDesc(String name);

    //NativeQuery
    @Query(
            value = "select * from Student s where s.age=?1",
            nativeQuery = true
    )
    Student getStudentsByAgeNative(Integer age);

    @Query(
            value = "select * from Student where age=?1",
            nativeQuery = true
    )
    Student getStudentsByAgeNativeTwo(Integer age);

    //Nsmed Param
    @Query(
            value = "select * from Student where age=:age",
            nativeQuery = true
    )
    Student getStudentsByAgeNativeNamedParam(@Param("age") Integer age);

        //modifying query

    @Modifying
    @Transactional
    @Query( value ="update Student set age=?1 where name=?2",nativeQuery = true)
    int updateAgeByName(Integer age,String name);

}
