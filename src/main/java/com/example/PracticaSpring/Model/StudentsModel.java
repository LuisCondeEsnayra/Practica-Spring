package com.example.PracticaSpring.Model;

import java.util.Map;

public record StudentsModel (
  String name,
  Map<String,Integer> scores
){
}
