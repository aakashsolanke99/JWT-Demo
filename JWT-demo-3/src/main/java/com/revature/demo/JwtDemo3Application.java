package com.revature.demo;

import com.revature.demo.models.Role;
import com.revature.demo.models.User;
import com.revature.demo.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class JwtDemo3Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(JwtDemo3Application.class, args);
	}

	public void run(String... args){
		User Adminuser= userRepository.findByRole(Role.ADMIN);

		if(null == Adminuser){
			User user=new User();
			user.setUserId(UUID.randomUUID().toString());
			user.setFirstName("Admin");
			user.setLastName("Admin");
			user.setEmail("admin@gmail.com");
			user.setPhoneNo("9325529741");
			user.setPassword(passwordEncoder.encode("Aakash@123"));
			user.setRole(Role.ADMIN);
			user.setAddress("this is admin");

			userRepository.save(user);

		}


	}
}
