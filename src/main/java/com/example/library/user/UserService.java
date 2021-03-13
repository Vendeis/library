package com.example.library.user;

import org.springframework.security.core.Authentication;

public interface UserService {

    ApplicationUser saveUser(ApplicationUser user);
    ApplicationUser findById(long id);
    ApplicationUser findByEmail(String email);
    void updateCode(String email, String code);
    void delete(long id);
//    String setConfig(UserConfig userConfig, Authentication auth);
//    UserConfig showConfig(Authentication auth);
}
