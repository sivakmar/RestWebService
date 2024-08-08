package com.springboot.RestWebService.restcontroller;

import com.springboot.RestWebService.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents=new ArrayList<>();
    @PostConstruct
    public void loadData(){
        theStudents.add(new Student("Siva","Kumar"));
        theStudents.add(new Student("Nivas","T"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }
    @GetMapping("/students/{studentId}")
    public Student getSpecificStudent(@PathVariable int studentId){

        if(studentId>=theStudents.size() || studentId<0)
            throw new StudentNotFoundException("Student Id not found "+ studentId);
        return theStudents.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exec)
    {
        StudentErrorResponse studentErrorResponse=new StudentErrorResponse();
        studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setMessage(exec.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exec){
        StudentErrorResponse studentErrorResponse=new StudentErrorResponse();
        studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        studentErrorResponse.setMessage(exec.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
