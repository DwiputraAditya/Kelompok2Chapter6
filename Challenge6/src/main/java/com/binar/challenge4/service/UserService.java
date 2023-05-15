package com.binar.challenge4.service;

import com.binar.challenge4.model.User;
import com.binar.challenge4.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException(String.format("Email tidak terdaftar")));
    }

    public User registerUser(User user){
        boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExist){
            throw new RuntimeException(
                    String.format("User with email '%s' already exist", user.getEmail())
            );
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        log.info("Get All Data User Success");
        return userRepository.findAll();
    }


    /*public User addUser(User user) {
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
        user1.setUserName(user.getUserName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        log.info("Update Data User Success");
        return userRepository.save(user1);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }*/
}
