package com.example.SercuitywithRest.Controller;

import com.example.SercuitywithRest.Model.UserModel;
import com.example.SercuitywithRest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestControllerApi {
      @Autowired
    private UserService userService;
   @PostMapping("/create")
    public String createdUser(@RequestBody UserModel user)throws Exception{
       try{
       userService.createUser(user);
       return "User created";
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }

   }
   @GetMapping("/login")
    public String logedin(@RequestParam String email, @RequestParam String password){
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
