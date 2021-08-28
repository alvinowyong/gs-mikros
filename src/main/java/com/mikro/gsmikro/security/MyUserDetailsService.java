package com.mikro.gsmikro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        if (userRepository.findByEmailAddress(username).isPresent()) {
            user = userRepository.findByEmailAddress(username).get();
            return new MyUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User with " + username + " not found.");
        }
    }

    public User findByEmailAddress(String emailAddress) {
        if (userRepository.findByEmailAddress(emailAddress).isPresent()) {
            return userRepository.findByEmailAddress(emailAddress).get();
        } else {
            return null;
        }
    }

    public void save(User user){
        userRepository.save(user);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
}