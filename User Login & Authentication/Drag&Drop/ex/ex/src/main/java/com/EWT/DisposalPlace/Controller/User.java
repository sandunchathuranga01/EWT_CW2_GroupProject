package com.EWT.DisposalPlace.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // Change
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/user")
@CrossOrigin
public class User {
    @GetMapping("/getUser")
    public String getUser() {
        return "simple-root";
    }

    @PostMapping("saveUser")
    public String saveUser(){
        return "user Saved!";
    }

    @PutMapping("UpdateUser")
    public String UpdateUser(){
        return "user updated!";
    }

    @DeleteMapping("DeleteUser")
    public String deleteUser(){
        return "user Deleted!";
    }
}