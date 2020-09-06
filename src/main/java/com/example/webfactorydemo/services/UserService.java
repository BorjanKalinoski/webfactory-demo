package com.example.webfactorydemo.services;

import com.example.webfactorydemo.models.GetUser;
import com.example.webfactorydemo.models.LoginUser;
import com.example.webfactorydemo.models.User;
import com.example.webfactorydemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public GetUser registerUser(User user) {//throws Exception
        User registeredUser = userRepository.save(new User(
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                user.getFullName()
        ));
        return new GetUser(registeredUser.getId(), registeredUser.getFullName(), registeredUser.getEmail());
    }

    public GetUser loginUser(LoginUser user) {
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if (u.isPresent()) {
            User usr = u.get();
            return new GetUser(usr.getId(), usr.getFullName(), usr.getEmail());
        }//throw exception
        return null;
    }
}
