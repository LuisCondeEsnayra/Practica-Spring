package com.example.PracticaSpring.Services;

import com.example.PracticaSpring.Model.StudentsModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentsService {
    List<StudentsModel> getStudents();
    List<StudentsModel> getStudentsByName(String name);
    List<StudentsModel> getExcellentStudents();
    Map<Integer, Set<String>> getGradeGroups();
    Map<String, List<String>> getStudentsBySubject();
    List<List<StudentsModel>> orderStudentsByGrade();
    Map<String, List<String>> getSubjects();
    Map<String,List<Integer>> getGrades();
 }
