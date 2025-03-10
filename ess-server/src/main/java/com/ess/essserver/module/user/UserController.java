package com.ess.essserver.module.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final List<UserModel> userModels = UserFakeRepository.getUsers(10);

    // A simple GET endpoint
    @GetMapping
    public List<UserModel> getUsers() {
        return userModels;
    }

    @GetMapping("/{id}")
    public UserModel findById(@PathVariable int id) {
        return userModels.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }
}