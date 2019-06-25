package com.utn.parcialLab.controller;


import com.utn.parcialLab.model.Comment;
import com.utn.parcialLab.model.Publication;
import com.utn.parcialLab.model.User;
import com.utn.parcialLab.service.PublicationService;
import com.utn.parcialLab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PublicationService publicationService;

    @GetMapping("")
    public List<User> getUsers(){
        return userService.getAll();
    }

    @PostMapping("")
    public User addUser(@RequestBody final User u, @RequestHeader(value = "User-Agent") final String browser){
        return userService.addUser(u, browser);
    }

    @PutMapping("/{id}")
    public User modifyUser(@RequestBody final User u, @PathVariable final Integer id){
        return userService.modifyUser(u);
    }

    @PostMapping("/{userId}/publication")
    public User addPublication(@RequestBody final Publication p, @PathVariable final Integer userId){
        User u = userService.findById(userId);
        p.setUser(u);
        p.setDate(LocalDate.now());
        u.getPublications().add(p);
        return userService.saveUser(u);
    }

    @PostMapping("/{userId}/{publicationId}/comment")
    public Publication addComment(@RequestBody final Comment c, @PathVariable("userId") final Integer userId, @PathVariable("publicationId") final Integer publicationId){
        User u = userService.findById(userId);
        Publication p = publicationService.findById(publicationId);

        c.setUser(u);
        c.setPublication(p);
        c.setDate(LocalDate.now());
        p.getCommentList().add(c);
        return publicationService.save(p);
    }



}
