package com.revature.demo.controllers;

import com.revature.demo.models.User;
import com.revature.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;
    @GetMapping("user")
    public List<User> getUser(){

        return this.userService.getUsers();
    }

    @GetMapping("/create-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();   // return the name of current login in user
    }
}
