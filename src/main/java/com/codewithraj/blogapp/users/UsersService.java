package com.codewithraj.blogapp.users;

import com.codewithraj.blogapp.users.dtos.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UsersService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserEntity createUser(CreateUserRequest u){
        UserEntity newUser = modelMapper.map(u, UserEntity.class);
        // TODO: encrypt and save password as well
        return userRepository.save(newUser);
    }

    public UserEntity getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
    public UserEntity getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
    public UserEntity loginUser(String username, String password){
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username){
            super("User " + username + " not found");
        }
        public UserNotFoundException(Long userId){
            super("User with id: " + userId + " not found");
        }
    }

}
