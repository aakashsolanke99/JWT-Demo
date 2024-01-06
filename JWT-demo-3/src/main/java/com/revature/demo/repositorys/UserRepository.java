package com.revature.demo.repositorys;

import com.revature.demo.models.Role;
import com.revature.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

   public Optional<User> findByEmail(String email);

   User findByRole(Role role);

}
