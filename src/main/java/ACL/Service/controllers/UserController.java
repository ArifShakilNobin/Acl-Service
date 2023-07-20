package ACL.Service.controllers;

import ACL.Service.models.User;
import ACL.Service.services.ImpService.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUSer(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/forAdmin")
    public String forAdmin(){
        return "this is for admin";
    }
}