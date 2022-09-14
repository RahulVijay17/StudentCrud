package com.praveen.crud.student.controller;
import com.praveen.crud.student.Exception.StudentNotFoundException;
import com.praveen.crud.student.dto.StudentDto;
import com.praveen.crud.student.helper.FileUploadHelper;
import com.praveen.crud.student.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class StudentResource {

    Logger logger= LoggerFactory.getLogger(StudentResource.class);

    @Autowired
    StudentService studentService;

    @Autowired
    private FileUploadHelper fileUploadHelper;

        @GetMapping("/student-name")
    public String name(){
        return "Rahul";
    }

    @PostMapping("/student")
    public StudentDto addStudent( @RequestBody StudentDto studentDto) {
        logger.info("Adding method");
                try{
                    studentDto= studentService.addStudent(studentDto);
                    return studentDto;
                }
                catch (Exception e)
                {
                    logger.error("null"+e);
                    return  studentDto;
                }

    }

    @GetMapping("/student")
    public List<StudentDto> getAllStudents() {
        List<StudentDto> studentDtoList = new ArrayList<>();
        try {
            studentDtoList = studentService.getAllStudent();
            return studentDtoList;
        } catch (Exception e) {
            logger.error("not registered");
            return studentDtoList;
        }
    }

    @GetMapping("/students")
    public List<StudentDto> getStudents() {
        List<StudentDto> studentDtoList = new ArrayList<>();
        try {
            studentDtoList = studentService.getAllStudent();
            return studentDtoList;
        } catch (Exception e) {
            logger.error("not registered");
            return studentDtoList;
        }
    }

    @GetMapping("/get-msg")
    public String getMsg() {
        return "Hi";
    }



    @GetMapping("/student/{id}")
    public StudentDto getstudent(@PathVariable Integer id) {
        logger.info("GetAll Student Available");
        StudentDto studentdto = new StudentDto();

        try{
         studentdto=studentService.getStudentById(studentdto);
        if (studentdto == null) {
        throw new StudentNotFoundException("student id not found - " + id);
     }
        return studentdto;
}
        catch (Exception e){
            logger.error("No USer Available"+e);
            return studentdto;

        }
    }

    @PutMapping("/student")
    public StudentDto updateStudentById(@RequestBody StudentDto studentDto) {
        logger.info("Updated Student");
        try {
            StudentDto updateStudent = studentService.updateStudent(studentDto);
            return studentDto;
        } catch (Exception e) {
            logger.error("Nothing changed"+e);
            return studentDto;
        }
    }
    @DeleteMapping("/student/{id}")

    public StudentDto deleteStudentById(@PathVariable Integer id) {
        logger.info("Delete Method");
            StudentDto studentDto=new StudentDto();
        try {
            StudentDto studentDto1=studentService.delStudentById(id);
            return studentDto;
        }
        catch (Exception e)
        {
            logger.error("Not Found This Id"+e);
            return studentDto;
        }
    }
    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Integer id) {
        // System.out.println(file.getOriginalFilename());
        // System.out.println(file.getSize());
        // System.out.println(file.getContentType());
        // System.out.println(file.getName());
        try {

            // validation
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
            }

            //
            if (!file.getContentType().equals("image/jpeg")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Only JPEG content type are allowed");

            }

            // file upload code..

            boolean f = fileUploadHelper.uploadFile(file,id);
            if(f) {
                // return ResponseEntity.ok("File is successfully uploaded");

                return ResponseEntity.ok(ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/image/")
                        .path(file.getOriginalFilename())
                        .toUriString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some went wrong ! Try again");
    }

    /*@PostMapping("/student")
    public ResponseEntity<Student> createStudent (@Valid  @RequestBody StudentDto student) {
        StudentDto savedStudent = studentService.addStudent(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedStudent.getId())
                .toUri();
        return ResponseEntity.created(location).build();*/

    }
