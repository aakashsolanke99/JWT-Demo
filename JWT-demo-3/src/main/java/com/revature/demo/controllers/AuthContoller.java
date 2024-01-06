package com.revature.demo.controllers;

import com.revature.demo.dto.SignUpRequest;
import com.revature.demo.models.JwtRequest;
import com.revature.demo.models.JwtResponse;
import com.revature.demo.models.User;
import com.revature.demo.repositorys.UserRepository;
import com.revature.demo.security.JWTHelper;
import com.revature.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("auth")
public class AuthContoller {

    @Autowired
    private UserDetailsService userDetailsService;  // for user details need to be fetch

    @Autowired
    private AuthenticationManager manager;   // for  authenicate

    @Autowired
    private JWTHelper helper;   // for creating jwt

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody SignUpRequest signUpRequest){
         return  (userService.createUser(signUpRequest));
    }


}
