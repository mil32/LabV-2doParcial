package com.utn.parcialLab.model;

import com.utn.parcialLab.repository.UserRepository;
import com.utn.parcialLab.service.UserService;
import lombok.Data;
import org.springframework.scheduling.annotation.Async;

import javax.persistence.Entity;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Entity
@Data
public class AllContent {




    @Async("Executor")
    public CompletableFuture<List<User>>getAllUsers(UserService userv) {

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {

        }
        return CompletableFuture.completedFuture(userv.getAll());

    }


}
