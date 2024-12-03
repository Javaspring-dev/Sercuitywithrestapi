package com.example.SercuitywithRest.Controller;

import com.example.SercuitywithRest.Model.UserModel;
import com.example.SercuitywithRest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RestControllerApi {
      @Autowired
    private UserService userService;
   @PostMapping("/create")
    public String createUser(@RequestBody UserModel user){
       userService.createUser(user);
       return "User created";
   }
   @GetMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password){
       try {
           UserModel user = userService.login(email,password);
           return "Login Sucessfully";
       } catch (Exception e) {
           return e.getMessage();
       }
   }
   @GetMapping("/user/{id}")
    public Object getUserById(@PathVariable long id){
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       if (principal instanceof UserModel){
           UserModel LogeedIn = (UserModel) principal;
           if (LogeedIn.getId().equals(id)|| "ADMIN".equals(LogeedIn.getRole())){
               return userService.getById(id).orElse(null);
           }else {
               return "Access denied";
           }
           }else{
            return "acess deined need to login";
           }
       }
   }
