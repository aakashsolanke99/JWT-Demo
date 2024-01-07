package com.revature.demo.dto;

import com.revature.demo.models.Role;
import lombok.Data;

@Data
public class SignUpRequest {

    private String FirstName;
    private String LastName;
    private String email;
    private String phoneNo;
    private String password;
    private String address;


}
