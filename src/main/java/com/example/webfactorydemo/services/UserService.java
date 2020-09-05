package com.example.webfactorydemo.services;

import com.example.webfactorydemo.models.GetUser;
import com.example.webfactorydemo.models.LoginUser;
import com.example.webfactorydemo.models.User;
import com.example.webfactorydemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public GetUser registerUser(User user) {
        User u = userRepository.save(user);
        return new GetUser(u.getId(), u.getFullName(), u.getEmail());
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
