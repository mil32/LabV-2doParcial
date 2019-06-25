package com.utn.parcialLab.service;

import com.utn.parcialLab.model.User;
import com.utn.parcialLab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class UserService {

    @Autowired
   private UserRepository userRepository;


    public User addUser(User u, String browser) {
        u.setBrowser(browser);
        return userRepository.save(u);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User modifyUser(User u) {
        //TODO
        return userRepository.save(u);
    }

    public User findById(Integer id){
        User u = userRepository.findById(id)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format("No se encontro el usuario Id nº %s", id)));
        return u;
    }

    public User saveUser(User u) {
        return userRepository.save(u);
    }
}
