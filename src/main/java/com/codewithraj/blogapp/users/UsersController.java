package com.codewithraj.blogapp.users;

import com.codewithraj.blogapp.common.dtos.ErrorResponse;
import com.codewithraj.blogapp.users.dtos.CreateUserRequest;
import com.codewithraj.blogapp.users.dtos.UserResponse;
import com.codewithraj.blogapp.users.dtos.LoginUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final ModelMapper modelMapper;

    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    ResponseEntity<UserResponse> signUpUser(@RequestBody CreateUserRequest request){
        UserEntity savedUser = usersService.createUser(request);
        URI savedUserUri = URI.create("/users/" + savedUser.getId());
        return ResponseEntity.created(savedUserUri)
                .body(modelMapper.map(savedUser, UserResponse.class));
    }
    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request){
        UserEntity savedUser = usersService.loginUser(request.getUsername(), request.getPassword());
        URI savedUserUri = URI.create("/users/" + savedUser.getId());

        return ResponseEntity.ok(modelMapper.map(savedUser, UserResponse.class));
    }
    @ExceptionHandler({
            UsersService.UserNotFoundException.class
    })
    ResponseEntity<ErrorResponse> handleUserNotFoundException(UserResponse ex){

        String message;
        HttpStatus status;

        message = "Something went wrong";
        status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse response = ErrorResponse.builder()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }

}
