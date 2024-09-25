package com.example.PracticaSpring.Services;

import com.example.PracticaSpring.Model.StudentsModel;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.coyote.http11.Constants.a;

@Service
public class StudentImplementationService implements StudentsService{
    List<StudentsModel> students= List.of(new StudentsModel("Luis", Map.of( "Ingles",9,"Matematicas",10)),
                    new StudentsModel("Alex", Map.of("Matematicas",8,"Ingles",8)),
                    new StudentsModel("Ale",Map.of("Matematicas",9)),
                    new StudentsModel("Vero",Map.of("Matematicas",9,"Ingles",10)),
                    new StudentsModel("Sam",Map.of("Ingles",8))

    );
    public List<StudentsModel> getStudents() {
        return students;
    }

    public List<StudentsModel> getStudentsByName(String name){
        return students.stream().filter(s -> s.name().equals(name)).toList();
    }
    public List<StudentsModel> getExcellentStudents(){
        return students.stream().filter(s -> s.scores().values().stream().anyMatch(v -> v >= 9 )).toList();
    }

    public Map<Integer,Set<String>> getScoreGroups(){
       Stream<Map<Integer,String>> scoreMap = students.stream().flatMap(s -> s.scores().values().stream().map(score-> Map.of(score,s.name())));

        return scoreMap.flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
                ));
    }

    public Map<String, List<String>> getStudentsBySubject(){
        Stream<Map.Entry<String,String>> subjects = students.stream().flatMap(student -> student.scores().keySet().stream().map(sub -> Map.entry(sub,student.name())));

        return subjects.collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    public List<List<StudentsModel>> orderStudentsByScore(){
        Map<Double, List<StudentsModel>> studentMap = students.stream()
                .collect(Collectors.groupingBy(
                        student -> student.scores().values().stream()
                                .mapToInt(Integer::intValue)
                                .average().orElse(0)
                ));
        return studentMap.values().stream().toList();
    }

    public Map<String,List<String>> getSubjects(){
        return students.stream().collect(Collectors.toMap(StudentsModel::name, s-> s.scores().keySet().stream().toList() ));
    }

    public Map<String,List<Integer>> getScores(){
        return students.stream().collect(Collectors.toMap(StudentsModel::name,s -> s.scores().values().stream().toList()));
    }
}


