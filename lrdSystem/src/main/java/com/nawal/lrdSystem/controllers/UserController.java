package com.nawal.lrdSystem.controllers;

import com.nawal.lrdSystem.model.UserEntity;
import com.nawal.lrdSystem.model.dto.UserDto;
import com.nawal.lrdSystem.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {
    UserService userService;
    ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        UserEntity savedUser = userService.addUser(userEntity);
        return new ResponseEntity<>(modelMapper.map(savedUser,UserDto.class), HttpStatus.CREATED);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserEntity> userEntity = userService.getAllUsers();
        List<UserDto> userDto = userEntity.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(path = "users/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable("id") Long id) {
        Optional<UserEntity> userEntity = userService.findByID(id);
        return userEntity.map(entity -> new ResponseEntity<>(modelMapper.map(entity, UserDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        if(!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            userDto.setId(id);
            UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
            UserEntity saveUser = userService.addUser(userEntity);
            return new ResponseEntity<>(modelMapper.map(saveUser, UserDto.class),HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "users/{id}")
    public ResponseEntity deleteUsers(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
