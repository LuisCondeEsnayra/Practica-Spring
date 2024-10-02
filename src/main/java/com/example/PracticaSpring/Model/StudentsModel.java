package com.example.PracticaSpring.Model;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "Students")
public record StudentsModel (
  String name,
  Map<String,Integer> grades
){
}
