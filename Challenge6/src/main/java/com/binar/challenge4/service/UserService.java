package com.binar.challenge4.service;

import com.binar.challenge4.model.User;
import com.binar.challenge4.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User addUser(User user) {
        log.info("Add Data User Success");
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        log.info("Get All Data User Success");
        return userRepository.findAll();
    }

    public Page<User> getAllUsersPagination(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<User> result = userRepository.findAll(request);
        log.info("Get All Data User Pagination Success");
        return result;
    }

    public Optional<User> getUserById(Long id) {
        log.info("Get Data User By Id Success");
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        User user1 = userRepository.findById(id).get();
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        log.info("Update Data User Success");
        return userRepository.save(user1);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
