package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.models.User;
import com.example.freemoneynoscam.repositories.EmailRepository;
import com.example.freemoneynoscam.repositories.JDBCwriter;
import com.example.freemoneynoscam.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.io.FileNotFoundException;
import java.util.ArrayList;

@Controller
public class EmailController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/getEmails")
    public String getEmails() throws FileNotFoundException {
        JDBCwriter JDBCwriter = new JDBCwriter();
        return "Emails collected from suckers:<br>" + JDBCwriter.printEmails();
    }

    @PostMapping(value = "/sign-up")
    public String createNewUser(WebRequest requestFromUser) {
        //Indsæt mail i databasen
        System.out.println(requestFromUser.getParameter("email"));
        if(EmailService.validateEmail(requestFromUser.getParameter("email"))) {
            JDBCwriter.insertEmailToDatabase(requestFromUser.getParameter("email"));
            return "redirect:/sign-up";
        }
        else {
            System.out.println("Email not valid");
            return "redirect:/failed-signup";
        }
    }

    @GetMapping("/show-user")
    public String user(Model model){
        User testUser = EmailRepository.fetchUser(); //Fetching the user object from the repository
        model.addAttribute("user", testUser);
        return "show-user";
    }

    @GetMapping("/show-all-users")
    public String allUsers(Model model){
        ArrayList<User> allUsers = EmailRepository.displayEmails1(); //Fetching the user object from the repository
        model.addAttribute("allUsers", allUsers);
        return "show-all-users";
    }

    @GetMapping("/sign-up")
    public String success(){
        return "success-signup";
    }

    @GetMapping("/failed-signup")
    public String fail() {
        return "failed-signup";
    }
}
