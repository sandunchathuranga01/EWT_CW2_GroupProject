package com.EWTsystem.EWT.system.UserLogingSystem.Admin;

import com.EWTsystem.EWT.system.Other.ResDTO;
import com.EWTsystem.EWT.system.Other.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class AdminController {

    @Autowired
    private ResDTO resDTO;

    @Autowired
    private AdminRepo adminRepo;


    @GetMapping("/login")
    public ResponseEntity<StandardResponse> authorizedUser(@RequestParam(value = "email") String username,
                                                           @RequestParam(value = "pws") String password){
        try {
            if (username == null || password == null) {
                return new ResponseEntity<>(
                        new StandardResponse(401, "Login failed: username or password is empty", null),
                        HttpStatus.BAD_REQUEST);
            }

            List<Admin> admins = adminRepo.findAllByEmail(username);

            if (admins.isEmpty()) {
                return new ResponseEntity<>(
                        new StandardResponse(404, "Login failed: User not found", null),
                        HttpStatus.NOT_FOUND);
            }


            Admin admin=admins.get(0);

            if (!admin.getPassword().equals(password)) {
                return new ResponseEntity<>(
                        new StandardResponse(401, "Login failed: Incorrect password", null),
                        HttpStatus.UNAUTHORIZED);
            }

            // User authenticated successfully, construct and return response
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setId(admin.getId());
            adminDTO.setFname(admin.getFname());
            adminDTO.setLname(admin.getLname());
            adminDTO.setEmail(admin.getEmail());


            return new ResponseEntity<>(
                    new StandardResponse(200, "Login successful", adminDTO),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(
                    new StandardResponse(500, "Internal Server Error", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


