package com.crossover.imageupload.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class WebController {
    @GetMapping("")
    public ModelAndView home(Model model) {
        return new ModelAndView("index.html");
    }
}
