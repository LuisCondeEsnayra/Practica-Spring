package com.example.PracticaSpring.Controller;

import com.example.PracticaSpring.Model.StudentsModel;
import com.example.PracticaSpring.Services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class StudentsController {

    @Autowired
    StudentsService students;

    @GetMapping("/students")
    public List<StudentsModel> getStudents(){
        return  students.getStudents();
    }
    @GetMapping("/student")
    public List<StudentsModel> getStudentsByName(@RequestParam String name){
        return students.getStudentsByName(name);
    }
    @GetMapping("/excelentStudents")
    public List<StudentsModel> getExcellentStudents(){
        return students.getExcellentStudents();
    }
    @GetMapping("/scoresByGroup")
    public Map<Integer, Set<String>> getScoreGroups(){
        return students.getScoreGroups();
    }
    @GetMapping("/studentsBySubject")
    public Map<String ,List<String>> getStudentsBySubject(){
        return students.getStudentsBySubject();
    }
    @GetMapping("/orderedStudentsByScore")
    public List<List<StudentsModel>> orderStudentsByScore(){
        return students.orderStudentsByScore();
    }

    @GetMapping("/getSubjects")
    public Map<String,List<String>> getSubjects(){
        return students.getSubjects();
    }

    @GetMapping("/getScores")
    public Map<String,List<Integer>> getScores(){
        return students.getScores();
    }

}
