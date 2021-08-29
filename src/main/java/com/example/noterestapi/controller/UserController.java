package com.example.noterestapi.controller;

import com.example.noterestapi.dto.ResponseDto;
import com.example.noterestapi.model.User;
import com.example.noterestapi.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        try {
            return ResponseEntity.ok(this.userService.getAllUsers());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/getUserById")
    public ResponseEntity<User> getUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(this.userService.getUserById(id));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<ResponseDto> addUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.addUser(user));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/insertTestData")
    public ResponseEntity<String> insertTestData(){
        try {
            userService.insertTestData();
            return ResponseEntity.ok("Test data insertion completed.");
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
