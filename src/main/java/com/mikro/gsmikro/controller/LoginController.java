package com.mikro.gsmikro.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import javax.servlet.http.Cookie;

@Controller
public class LoginController {


    // Catching some minor differences in path
    @GetMapping(value = {"","/"})
    public String index(){
        // If user is already logged in, redirect to home page instead.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "index";
        }
        return "redirect:home";
    }

    @GetMapping("/login")
    public String login() {
        // If user is already logged in, redirect to home page instead.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:home";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request, HttpServletResponse response){
        // Clear context holder so that user becomes "anonymous" to spring security
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);

        // Invalidate sessions for security purposes
        if(session != null) {
            session.invalidate();
        }

        // Delete all cookies
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return "redirect:loggedout";
    }

    // Display logout success message
    @GetMapping("/loggedout")
    public String loggedOut(Model model) {
        model.addAttribute("message","logout");
        return "login";
    }
}
