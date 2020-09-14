package uk.me.ruck.bpdtstest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.me.ruck.bpdtstest.model.User;
import uk.me.ruck.bpdtstest.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequestMapping("/api/v1")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/london")
    public ResponseEntity<List<User>> getLondonUsers(){
        List<User> usersInLondon = userService.getUsersInLondon();
        List<User> usersNearLondon = userService.getUsersNearLondon();
        List<User> londonUsers = Stream.concat(usersInLondon.stream(), usersNearLondon.stream())
                .distinct()
                .collect(Collectors.toList());

        log.info("Users in London: {}", usersInLondon.size());
        log.info("Users near London: {}", usersNearLondon.size());
        log.info("Users in or near London: {}", londonUsers.size());

        return new ResponseEntity<>(londonUsers, HttpStatus.OK);
    }
}
