package com.example.SercuitywithRest.Controller;

import com.example.SercuitywithRest.Model.UserModel;
import com.example.SercuitywithRest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class RestControllerApi {
      @Autowired
    private UserService userService;
      @GetMapping
      @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<UserModel> getAllUser(){
          return userService.getAllUsers();
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public UserModel createuser(@RequestBody UserModel userModel){
          return userService.createUser(userModel);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserModel getUserByID(@PathVariable Long id){
          return userService.getUserById(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id){
           userService.deleteUserById(id);
    }

}
