package com.revature.demo.dto;

import com.revature.demo.models.Role;
import lombok.Data;

@Data
public class SignUpRequest {

    private String name;
    private String email;
    private String password;
    private String about;


}
