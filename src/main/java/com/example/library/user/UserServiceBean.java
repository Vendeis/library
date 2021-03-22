package com.example.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBean implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceBean(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public ApplicationUser saveUser(ApplicationUser user) {
        userRepository.save(user);
        return user;
    }


    @Override
    public ApplicationUser findById(long id){
        ApplicationUser user = userRepository.findById(id);
        return user;
    }

    public ApplicationUser findByEmail(String email){
        ApplicationUser user = userRepository.findByEmail(email);
        return user;
    }
    @Override
    public void updateCode(String email, String code){
        ApplicationUser user = userRepository.findByEmail(email);
        user.setVerificationCode(code);
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {

    }


//    @Override
//    public UserConfig showConfig(Authentication auth) {
//        auth.getPrincipal();
//        User user = userRepository.findByEmail(auth.getName());
//        return userConfigRepository.findByUser(user);
//    }
}
