package com.utn.parcialLab.controller;


import com.utn.parcialLab.model.AllContent;
import com.utn.parcialLab.model.Comment;
import com.utn.parcialLab.model.Publication;
import com.utn.parcialLab.model.User;
import com.utn.parcialLab.repository.ComentsxPublications;
import com.utn.parcialLab.service.PublicationService;
import com.utn.parcialLab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PublicationService publicationService;

    @Autowired
    private AllContent allContent;

    @GetMapping("/user")
    public List<User> getUsers(){
        return userService.getAll();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody final User u, @RequestHeader(value = "User-Agent") final String browser){
        return userService.addUser(u, browser);
    }

    @PutMapping("/user/{id}")
    public User modifyUser(@RequestBody final User u, @PathVariable final Integer id){
        return userService.modifyUser(u);
    }

    @PostMapping("/publication/{userId}")
    public User addPublication(@RequestBody final Publication p, @PathVariable final Integer userId){
        User u = userService.findById(userId);
        p.setUser(u);
        p.setDate(LocalDate.now());
        u.getPublications().add(p);
        return userService.saveUser(u);
    }

    @PostMapping("/comment/{userId}/{publicationId}")
    public Publication addComment(@RequestBody final Comment c, @PathVariable("userId") final Integer userId, @PathVariable("publicationId") final Integer publicationId){
        User u = userService.findById(userId);
        Publication p = publicationService.findById(publicationId);

        c.setUser(u);
        c.setPublication(p);
        c.setDate(LocalDate.now());
        p.getCommentList().add(c);
        return publicationService.save(p);
    }

    @GetMapping("/proyection/{publicationId}")
    public ComentsxPublications getCount(@PathVariable("publicationId") final Integer pubId ){
        return publicationService.getCount(pubId);
    }

    @GetMapping("/allContent")
    public ResponseEntity<?> getAll(){

        CompletableFuture<List<User>> userlist = allContent.getAllUsers(userService);
        CompletableFuture<List<Publication>> publicationList = allContent.getAllPublications(publicationService);

        List<Object> todo = userlist.join() + publicationList.join();

        //list.addAll(allContent.getAllUsers(userService));
        //list.addAll(userService.getAll());



        return ResponseEntity.status(200).body(list);
    }


}
