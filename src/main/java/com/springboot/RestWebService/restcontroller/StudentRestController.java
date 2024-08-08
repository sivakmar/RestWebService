package com.springboot.RestWebService.restcontroller;

import com.springboot.RestWebService.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return theStudents.get(studentId);
    }
}
