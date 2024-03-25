package com.EWTsystem.EWT.system.UserLogingSystem.Client;

import com.EWTsystem.EWT.system.Other.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRpo userRpo;
    @Autowired
    private ModelMapper modelMapper;

    public String saveUser(AuthUserDTO authUserDTO){
        if (userRpo.existsById(authUserDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            userRpo.save(modelMapper.map(authUserDTO , Users.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateUser(AuthUserDTO authUserDTO){
        if (userRpo.existsById(authUserDTO.getId())){
            userRpo.save(modelMapper.map(authUserDTO, Users.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<AuthUserDTO> getAllUser(){
        List<Users> userList=userRpo.findAll();
        return modelMapper.map(userList,new TypeToken<List<AuthUserDTO>>(){}.getType());
    }

    public String deleteUser(int locationID){
        if (userRpo.existsById(locationID)){
            userRpo.deleteById(locationID);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public String updatePassword(String email, String newPassword) {
        List<Users> users = userRpo.findAllByEmail(email);

        if (users.isEmpty()) {
            return VarList.RSP_NO_DATA_FOUND;
        }

        Users user = users.get(0);
        user.setPassword(newPassword);
        userRpo.save(user);

        return VarList.RSP_SUCCESS;
    }


}
