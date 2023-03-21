package com.uco.inventapp.service;

import java.util.ArrayList;

import com.uco.inventapp.domain.RedUser;
import com.uco.inventapp.repository.IRedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IRedUserRepository iredUserRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RedUser user = iredUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public RedUser save(RedUser redUser) {
        redUser.setPassword(bcryptEncoder.encode(redUser.getPassword()));
        return iredUserRepository.save(redUser);
    }

}