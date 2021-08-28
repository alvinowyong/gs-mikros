package com.mikro.gsmikro.controller;

import com.mikro.gsmikro.security.MyUserDetailsService;
import com.mikro.gsmikro.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class HomeController {
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal Authentication authentication, Principal principal) throws ParseException {
        // Update last login
        String principalEmail = principal.getName();
        User principalUser = myUserDetailsService.findByEmailAddress(principalEmail);
        principalUser.setLastLogin(LocalDateTime.now());
        myUserDetailsService.save(principalUser);

        String firstName = principalUser.getFirstName();
        model.addAttribute("firstName", firstName);

        // Aesthetics Function for HTML
        LocalTime now = LocalTime.now();
        String timeofDay = null;
        if (now.isAfter(LocalTime.parse("17:00"))){
            timeofDay = "Evening";
        } else if (now.isAfter(LocalTime.parse("12:00"))) {
            timeofDay = "Afternoon";
        } else if (now.isAfter(LocalTime.parse("00:00"))){
            timeofDay = "Morning";
        }
        model.addAttribute("MAE",timeofDay);

        // Fetch All Subscribed Vessels Alerts
        return "home";
    }
}
