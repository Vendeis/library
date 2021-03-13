package com.example.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping(value = "/register")
    public void register(@RequestBody ApplicationUser user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
    }
    @PostMapping(value = "/login")
    public void login(@RequestBody ApplicationUser user){

    }

//    @PostMapping(value = "/config")
//    public String setConfig(@RequestBody UserConfig userConfig, Authentication auth){
//        return userService.setConfig(userConfig, auth);
//    }
//
//    @GetMapping(value = "/config/show")
//    public UserConfig showConfig(Authentication auth){
//        return userService.showConfig(auth);
//    }
}
