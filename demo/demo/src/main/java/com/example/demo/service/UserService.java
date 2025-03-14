package com.example.demo.service;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@Slf4j
public class UserService {
   UserRepository userRepository;
   UserMapper userMapper;
   PasswordEncoder  passwordEncoder;
//    public User createUser(UserCreationRequest request){
//
//        if(userRepository.existsByUsername(request.getUsername()))
//            throw new RuntimeException("ErrorCode.USER_EXISTED");
//      //  User user = userMapper.toUser(request);
////        User user = new User();
////        user.setUsername(request.getUsername());
////        user.setPassword(request.getPassword());
////        user.setFirstName(request.getFirstName());
////        user.setLastName(request.getLastName());
////        user.setDob(request.getDob());
//       // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//    //    user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        HashSet<String > roles = new  HashSet<>();
//        roles .add(Role.USER.name());
//    //    user.setRoles(roles);
//      //  return userRepository.save(user);
//    }

//    public UserResponse updateUser(String userId, UserUpdateRequest request) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//
////        userMapper.updateUser(user, request);
////
////
////        return userMapper.toUserResponse(userRepository.save(user));
//    }


    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }



    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

   // @PreAuthorize("hasRole('ADMIN')")
    //@PostAuthorize("returnOjbect.username == authenication.name")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }
}