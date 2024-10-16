package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cms.entities.User;
import com.cms.forms.UserForm;
import com.cms.helpers.Message;
import com.cms.helpers.MessageType;
import com.cms.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {
    
	@Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    
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
    
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // this is showing login page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    // registration page
    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();
        // default data bhi daal sakte hai
//         userForm.setName("Durgesh");
//         userForm.setAbout("This is about : Write something about yourself");
        model.addAttribute("userForm", userForm);

        return "register";
    }
    
 // processing register

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("Processing registration");
        // fetch form data
        // UserForm
        System.out.println(userForm);

        // validate form data
        if (rBindingResult.hasErrors()) {
            return "register";
        }

        // TODO::Validate userForm[Next Video]

        // save to database

        // userservice

        // UserForm--> User
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic(
        // "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic(
                "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75");

        User savedUser = userService.saveUser(user);

        System.out.println("user saved :");

//        String message = "Registration Successful";

        // add the message:

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        // redirectto login page
        return "redirect:/register";
    }
    
}
