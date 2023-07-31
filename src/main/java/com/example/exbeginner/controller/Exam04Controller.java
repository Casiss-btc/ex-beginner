package com.example.exbeginner.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.exbeginner.domain.User;
import com.example.exbeginner.form.UserForm;

@Controller
@RequestMapping("/exam04")
public class Exam04Controller {
    @GetMapping("")
    public String index(Model model, UserForm form) {
        return "exam04";
    }
    @PostMapping("/create")
    public String create(@Validated UserForm form,BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if(result.hasErrors()) {
            return index(model, form);
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/exam04/toresult";
    }
    @GetMapping("/toresult")
    public String toresult() {
        return "exam04-result";
    }
}
