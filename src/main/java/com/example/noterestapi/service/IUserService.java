package com.example.noterestapi.service;

import com.example.noterestapi.dto.ResponseDto;
import com.example.noterestapi.model.User;

import java.util.List;

public interface IUserService {
    ResponseDto login(User user);
    ResponseDto addUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);

    void insertTestData();
}
