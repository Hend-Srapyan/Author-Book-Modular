package com.example.authorbookweb.controller;

import com.example.authorbookweb.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
       if (currentUser != null) {
           modelMap.put("user", currentUser.getUser());
       }
        return "index";
    }
}
