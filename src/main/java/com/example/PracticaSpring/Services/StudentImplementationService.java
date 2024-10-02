package com.example.PracticaSpring.Services;

import com.example.PracticaSpring.Model.StudentsModel;
import com.example.PracticaSpring.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class StudentImplementationService implements StudentsService{
    @Autowired
    StudentsRepository studentsRepository;
    public List<StudentsModel> getStudents() {
        List<StudentsModel> list = studentsRepository.findAll();
        System.out.println(list);
        return list;

    }

    public List<StudentsModel> getStudentsByName(String name){
        return studentsRepository.findByTheStudentName(name);
    }

    public List<StudentsModel> getExcellentStudents(){
        return studentsRepository.findAll().stream().filter(s -> s.grades().values().stream().anyMatch(v -> v >= 9 )).toList();
    }

    public Map<Integer,Set<String>> getGradeGroups(){
       Stream<Map<Integer,String>> gradeMap = studentsRepository.findAll().stream().flatMap(s -> s.grades().values().stream().map(grade-> Map.of(grade,s.name())));

        return gradeMap.flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
                ));
    }

    public Map<String, List<String>> getStudentsBySubject(){
        Stream<Map.Entry<String,String>> subjects = studentsRepository.findAll().stream().flatMap(student -> student.grades().keySet().stream().map(sub -> Map.entry(sub,student.name())));

        return subjects.collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    public List<List<StudentsModel>> orderStudentsByGrade(){
        Map<Double, List<StudentsModel>> studentMap =  studentsRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        student -> student.grades().values().stream()
                                .mapToInt(Integer::intValue)
                                .average().orElse(0)
                ));
        return studentMap.values().stream().toList();
    }

    public Map<String,List<String>> getSubjects(){
        return studentsRepository.findAll().stream().collect(Collectors.toMap(StudentsModel::name, s-> s.grades().keySet().stream().toList() ));
    }

    public Map<String,List<Integer>> getGrades(){
        return studentsRepository.findAll().stream().collect(Collectors.toMap(StudentsModel::name,s -> s.grades().values().stream().toList()));
    }
}


