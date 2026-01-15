package com.fitness.userservice.services;

import com.fitness.userservice.dtos.RegisterRequest;
import com.fitness.userservice.dtos.UserResponse;
import com.fitness.userservice.models.User;
import com.fitness.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserResponse registerUser(RegisterRequest registerRequest) {

        //validations
        if(userRepository.existsByEmail(registerRequest.getEmail()))
        {
            //throw new RuntimeException("Email already exists!!"); as it's for the User-Service user data not for keycloak users
            User existingUser=userRepository.findByEmail(registerRequest.getEmail());
            UserResponse userResponse=new UserResponse();
            userResponse.setId(existingUser.getId());
            userResponse.setEmail(existingUser.getEmail());
            userResponse.setPassword(existingUser.getPassword());
            userResponse.setFirstName(existingUser.getFirstName());
            userResponse.setLastName(existingUser.getLastName());
            userResponse.setCreatedAt(existingUser.getCreatedAt());
            userResponse.setUpdateAt(existingUser.getUpdateAt());
        }
        
        User user=new User();
        user.setEmail(registerRequest.getEmail());
        user.setKeycloakId(registerRequest.getKeycloakId());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(registerRequest.getPassword());
        //user.setPassword(registerRequest.getPassword());

        User savedUser=userRepository.save(user);
        UserResponse userResponse=new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setKeycloakId(savedUser.getKeycloakId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdateAt(savedUser.getUpdateAt());

        return userResponse;
    }

    public UserResponse getUserProfile(String userId) {

        User user=userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Id does not Exists!!"));
        UserResponse userResponse=new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setKeycloakId(user.getKeycloakId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdateAt(user.getUpdateAt());
        return userResponse;
    }

    public Boolean existByUserId(String userId) {

        log.info("Calling User Validation API for userId: {}", userId);
        //return userRepository.existsByUserId(userId); calls User Id from the User-Service
        return userRepository.existsByKeycloakId(userId); // calls userId from the Keycloak user data
    }

}
