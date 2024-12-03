package com.example.SercuitywithRest.Service;

import com.example.SercuitywithRest.Model.UserModel;
import com.example.SercuitywithRest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

   public UserModel createUser(UserModel userModel){
       userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
       return userRepository.save(userModel);
   }
   public UserModel login(String email, String password)throws Exception{
       Optional<UserModel> userOptional = userRepository.findUsername(email);
       if (userOptional.isPresent()) {
           UserModel user = userOptional.get();

           if (passwordEncoder.matches(password, user.getPassword() )) {
            return user;
           }else {
               throw new Exception("Invalid Credentials");
           }
       }else {
           throw new Exception("User not found");
       }

   }
   public List<UserModel> getAllUser(){
       return userRepository.findAll();
   }
   public Optional<UserModel> getById(Long id){
       return userRepository.findById(id);
   }
   public void logout(){
       SecurityContextHolder.clearContext();
   }

}
