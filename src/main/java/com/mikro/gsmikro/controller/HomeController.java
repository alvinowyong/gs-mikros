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

    @GetMapping("/home/remittance")
    public String remittance(){
        return "remit";
    }

    @GetMapping("/remittance")
    public String guestremittance(){
        return "guest_remit";
    }

    @GetMapping("/home/invest")
    public String invest(){
        return "invest";
    }

    @GetMapping("/invest")
    public String guestinvest(){
        return "guest_invest";
    }

    @GetMapping("/home/save")
    public String save(){
        return "save";
    }

    @GetMapping("/save")
    public String guestsave(){
        return "guest_save";
    }

    @GetMapping("/home/borrow")
    public String borrow(){
        return "borrow";
    }

    @GetMapping("/borrow")
    public String guestborrow(){
        return "guest_borrow";
    }

    @GetMapping("/home/learn")
    public String learn(){
        return "learn";
    }

    @GetMapping("/learn")
    public String guestlearn(){
        return "guest_learn";
    }
}
