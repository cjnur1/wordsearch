package com.example.wordsearch.controller;

import com.example.wordsearch.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
public class Controller {

@Autowired
Service service;
@GetMapping("/wordsearch")
public  String grid(@RequestParam int gridSize,@RequestParam List<String> words)
{
char[][] content=service.genarateGrid(gridSize,words);
String gridToString="";
   for (int i = 0; i < content.length; i++) {
      for (int j = 0; j < content.length; j++) {
        gridToString+=content[i][j] + " ";
      }
      gridToString+="<br>";
   }
   return gridToString;
}
}
