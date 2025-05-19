package com.Book_Mart.OOP.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home.html";
    }
}
//
//This is for changing the address when I run localhost/8080 on my browser