package com.praveen.crud.student.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import com.praveen.crud.student.model.Student;
import com.praveen.crud.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Component
@Embeddable
@AttributeOverrides({
        @AttributeOverride(
                name="photo",
                column = @Column(name = "Student_photo")
        )
})
public class FileUploadHelper {

    @Autowired
    StudentRepository studentRepository;

    public final String UPLOAD_DIR="C:\\Users\\HP\\Downloads\\StudentCrud\\src\\main\\resources\\static";

    public boolean uploadFile(MultipartFile multipartFile,Integer id)
    {
        boolean f=false;

        try {

                InputStream is= multipartFile.getInputStream();
                byte data[]=new byte[is.available()];
                is.read(data);

                String fileName = multipartFile.getOriginalFilename();
                FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+File.separator+multipartFile
                        .getOriginalFilename());

                fos.write(data);
                fos.flush();
                fos.close();
            Files.copy(multipartFile.getInputStream(),
                    Paths.get(UPLOAD_DIR+File
                            .separator+multipartFile
                            .getOriginalFilename()) ,
                    StandardCopyOption.REPLACE_EXISTING) ;
            f=true;
            Optional<Student> student = studentRepository.findById(id);

            student.get().setPhoto(fileName);
            studentRepository.save(student.get());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }


}
