package com.example.exbeginner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;


@Controller
@RequestMapping("/exam03")

public class Exam03Controller {
    @Autowired
    private ServletContext application;
    @GetMapping("")
    public String index() {
        return "exam03";
    }
    @PostMapping("/exam-03-receive")
    public String inputForm(int num1,int num2,int num3) {
        int result = num1+num2+num3;
        int result_tax = (int) ((double)result*1.1);
        application.setAttribute("result", result);
        application.setAttribute("result_tax", result_tax);
        return "exam03-result";
        
    }
    
}
