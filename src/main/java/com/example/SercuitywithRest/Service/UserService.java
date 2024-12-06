package com.example.SercuitywithRest.Service;

import com.example.SercuitywithRest.Model.UserModel;
import com.example.SercuitywithRest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

   public UserModel createUser(UserModel userModel){
       userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
       return userRepository.save(userModel);
   }
   public UserModel login(String name, String password)throws Exception{
      Optional<UserModel> userOptional = UserRepository.findByUserName(name);
       if (userOptional.isPresent()) {
           UserModel user = userOptional.get();

           if (passwordEncoder.matches(password, user.getPassword() )) {
               UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,null);
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
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
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if (authentication !=null && authentication.isAuthenticated()){
           authentication.getName();
       }

       return userRepository.findById(id);
   }
   public void logout(){
       SecurityContextHolder.clearContext();
   }

}
