package com.mikro.gsmikro.controller;

import com.mikro.gsmikro.security.MyUserDetailsService;
import com.mikro.gsmikro.security.User;
import com.mikro.gsmikro.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class HomeController {
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    UserRepository userRepository;

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

        model.addAttribute("savings",principalUser.getBalance());
        model.addAttribute("fixed",principalUser.getFixeddeposits());
        model.addAttribute("investments",principalUser.getInvestments());
        model.addAttribute("firstname",principalUser.getFirstName());

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
    public String invest(Model model, @AuthenticationPrincipal Authentication authentication, Principal principal){
        String principalEmail = principal.getName();
        User principalUser = myUserDetailsService.findByEmailAddress(principalEmail);
        model.addAttribute("firstname",principalUser.getFirstName());
        model.addAttribute("fixed",principalUser.getFixeddeposits());
        model.addAttribute("investments",principalUser.getInvestments());

        return "invest";
    }

    @GetMapping("/invest")
    public String guestinvest(){
        return "guest_invest";
    }

    @GetMapping("/home/save")
    public String save(Model model, @AuthenticationPrincipal Authentication authentication, Principal principal){
        String principalEmail = principal.getName();
        User principalUser = myUserDetailsService.findByEmailAddress(principalEmail);
        model.addAttribute("savings",principalUser.getBalance());
        model.addAttribute("firstname",principalUser.getFirstName());

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
