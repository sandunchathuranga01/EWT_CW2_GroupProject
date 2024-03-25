package com.EWTsystem.EWT.system.UserLogingSystem.Client;

import com.EWTsystem.EWT.system.Other.ResDTO;
import com.EWTsystem.EWT.system.Other.StandardResponse;
import com.EWTsystem.EWT.system.Other.VarList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/Client")
public class UserController {

    @Autowired
    private ResDTO resDTO;

    @Autowired
    private UserRpo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<StandardResponse> authorizedUser(@RequestParam(value = "email") String username,
                                                           @RequestParam(value = "pws") String password){
        try {
            if (username == null || password == null) {
                return new ResponseEntity<>(
                        new StandardResponse(401, "Login failed: username or password is empty", null),
                        HttpStatus.BAD_REQUEST);
            }

            List<Users> users = userRepo.findAllByEmail(username);

            if (users.isEmpty()) {
                return new ResponseEntity<>(
                        new StandardResponse(404, "Login failed: User not found", null),
                        HttpStatus.NOT_FOUND);
            }

            Users user = users.get(0);

            if (!user.getPassword().equals(password)) {
                return new ResponseEntity<>(
                        new StandardResponse(401, "Login failed: Incorrect password", null),
                        HttpStatus.UNAUTHORIZED);
            }

            // User authenticated successfully, construct and return response
            AuthUserDTO authUserDTO = new AuthUserDTO();
            authUserDTO.setId(user.getId());
            authUserDTO.setAddress(user.getAddress());
            authUserDTO.setFname(user.getFname());
            authUserDTO.setLname(user.getLname());
            authUserDTO.setEmail(user.getEmail());
            authUserDTO.setMobileNumber(user.getMobileNumber());

            return new ResponseEntity<>(
                    new StandardResponse(200, "Login successful", authUserDTO),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(
                    new StandardResponse(500, "Internal Server Error", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@RequestBody AuthUserDTO authUserDTO) {
        try {
            String res = userService.saveUser(authUserDTO);
            if (res.equals("00")) {
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Success");
                resDTO.setContent(authUserDTO);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);
            }
            else if (res.equals("06")) {
                resDTO.setCode(VarList.RSP_DUPLICATED);
                resDTO.setMessage("Disposal place Already Registered");
                resDTO.setContent(authUserDTO);
                return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);

            }
            else {
                resDTO.setCode(VarList.RSP_FAIL);
                resDTO.setMessage("Error");
                resDTO.setContent(null);
                return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);
        }
    }


    @PutMapping("/updatePassword")
    public ResponseEntity updatePassword(@RequestParam(value = "email") String email,
                                         @RequestParam(value = "newPassword") String newPassword) {
        try {
            String res = userService.updatePassword(email, newPassword);
            if (res.equals(VarList.RSP_SUCCESS)) {
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Password updated successfully");
                resDTO.setContent(null);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);
            } else if (res.equals(VarList.RSP_NO_DATA_FOUND)) {
                resDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                resDTO.setMessage("User not found with the provided email");
                resDTO.setContent(null);
                return new ResponseEntity(resDTO, HttpStatus.NOT_FOUND);
            } else {
                resDTO.setCode(VarList.RSP_FAIL);
                resDTO.setMessage("Failed to update password");
                resDTO.setContent(null);
                return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAllUser")
    public ResponseEntity getAllUser(){
        try {
            List<AuthUserDTO> userList = userService.getAllUser();
            resDTO.setCode(VarList.RSP_SUCCESS);
            resDTO.setMessage("Success");
            resDTO.setContent(userList);
            return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        try {
            String res = userService.deleteUser(id);
            if (res.equals("00")) {
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Success");
                resDTO.setContent(null);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);
            } else {
                resDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                resDTO.setMessage("No Employee Available For this empID");
                resDTO.setContent(null);
                return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(e.getMessage());
            resDTO.setContent(e);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


