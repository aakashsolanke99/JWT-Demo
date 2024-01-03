package com.revature.demo.services;

import com.revature.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> store=new ArrayList<>();

    public  UserService(){
        store.add(new User(UUID.randomUUID().toString(),"Aakash Solanke","aakashsolanke99@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(),"Krunal Zodape","krunal@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(),"Rupali Zoadpe","rupali@gmail.com"));

        store.add(new User(UUID.randomUUID().toString(),"Veera Rajput","veera@gmail.com"));

    }

    public List<User> getUsers(){
        return this.store;
    }



}
