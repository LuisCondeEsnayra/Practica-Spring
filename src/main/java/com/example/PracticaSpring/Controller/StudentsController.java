package com.example.PracticaSpring.Controller;

import com.example.PracticaSpring.Model.StudentsModel;
import com.example.PracticaSpring.Services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class StudentsController {

    @Autowired
    StudentsService students;

    @GetMapping("/students")
    public List<StudentsModel> getStudents(){
        List<StudentsModel> list = students.getStudents();
        return new ResponseEntity<>(list, HttpStatus.OK).getBody();
    }
    @GetMapping("/student")
    public List<StudentsModel> getStudentsByName(@RequestParam String name){
        return new ResponseEntity<>( students.getStudentsByName(name), HttpStatus.OK).getBody() ;
    }
    @GetMapping("/excelentStudents")
    public List<StudentsModel> getExcellentStudents(){

        return new ResponseEntity<>(students.getExcellentStudents(), HttpStatus.OK).getBody() ;
    }
    @GetMapping("/gradesByGroup")
    public Map<Integer, Set<String>> getGradeGroups(){
        return new ResponseEntity<>(students.getGradeGroups(), HttpStatus.OK).getBody() ;
    }
    @GetMapping("/studentsBySubject")
    public Map<String ,List<String>> getStudentsBySubject(){
        return new ResponseEntity<>(students.getStudentsBySubject(), HttpStatus.OK).getBody() ;
    }
    @GetMapping("/orderedStudentsByGrade")
    public List<List<StudentsModel>> orderStudentsByGrade(){
        return new ResponseEntity<>(students.orderStudentsByGrade(), HttpStatus.OK).getBody() ;
    }

    @GetMapping("/getSubjects")
    public Map<String,List<String>> getSubjects(){
        return new ResponseEntity<>(students.getSubjects(), HttpStatus.OK).getBody() ;
    }

    @GetMapping("/getGrades")
    public Map<String,List<Integer>> getgrades(){
        return new ResponseEntity<>(students.getGrades(), HttpStatus.OK).getBody() ;
    }

}
