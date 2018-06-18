package com.devquartzo.soundtracker.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("soundtracker")
public class SoundTrackerController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping()
    public String get(Model model) {

        model.addAttribute("name", appName + "Bla");

        return "soundtracker/index";
    }

}
