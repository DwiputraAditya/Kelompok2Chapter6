package com.binar.challenge4.controller;

import com.binar.challenge4.model.User;
import com.binar.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User user1 = userService.addUser(user);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/getAllUserPagination")
    public Page<User> getAllUsersPagination(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsersPagination(page, size);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "userId") Long id) {
        Optional<User> userData = userService.getUserById(id);
        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestParam(value = "userId") Long id, @RequestBody User user){
        userService.updateUser(id,user);
        return new ResponseEntity<>("Data berhasil di update", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<String> deleteUserById(@RequestParam(name = "userId") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Data Berhasil dihapus");
    }
}
