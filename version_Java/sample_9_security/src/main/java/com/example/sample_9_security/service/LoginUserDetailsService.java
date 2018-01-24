package com.example.sample_9_security.service;


import com.example.sample_9_security.domain.User;
import com.example.sample_9_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findOne(username);

        if (user == null) { throw new UsernameNotFoundException("The requested user is not found."); }

        return new LoginUserDetails(user);
    }
}