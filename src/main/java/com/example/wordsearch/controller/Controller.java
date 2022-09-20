package com.example.wordsearch.controller;

import com.example.wordsearch.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

@Autowired
Service service;

@GetMapping("/")
public ModelAndView grid(ModelAndView modelAndView,@RequestParam int gridSize, @RequestParam List<String> words) {
    char[][] content = service.genarateGrid(gridSize,
            words);
    String gridToString = "";
    for (int i = 0; i < content.length; i++) {
        for (int j = 0; j < content.length; j++) {
            gridToString += content[i][j] + " ";
        }
        gridToString += "";
    }
    modelAndView.setViewName("index");
    modelAndView.addObject("grid",content);
    return modelAndView;
}
}

