package com.example.PracticaSpring.repository;
import com.example.PracticaSpring.Model.StudentsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface StudentsRepository extends MongoRepository<StudentsModel, Integer> {


    @Query("{ 'name' : ?0 }")
    List<StudentsModel> findByTheStudentName(String name);

}