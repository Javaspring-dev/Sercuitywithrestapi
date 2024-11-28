package com.example.SercuitywithRest.Service;

import com.example.SercuitywithRest.Model.UserModel;
import com.example.SercuitywithRest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public List<UserModel> getAllUsers(){
        return userRepo.findAll();
    }
    public UserModel getUserById(Long id){
        return userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }
    public UserModel createUser(UserModel userModel){
        return userRepo.save(userModel);
    }
    public void deleteUserById(Long id){
        userRepo.deleteById(id);
    }
}
