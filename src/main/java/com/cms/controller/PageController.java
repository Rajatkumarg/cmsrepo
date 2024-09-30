package com.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class PageController {
    
    @GetMapping("/home")
    public String home(Model model){
    	model.addAttribute("YouTube", "https://www.youtube.com/watch?v=SAqi7zmW1fY&t=5619s");
    	model.addAttribute("AppName","CMS");
        return "home";
    }
    
    @GetMapping("/about")
    public String aboutPage(Model model) {
        System.out.println("About page loading");
        return "about";
    }
    
    @GetMapping("/services")
    public String servicesPage(Model model) {
        System.out.println("Services page loading");
        return "services";
    }
    
}
