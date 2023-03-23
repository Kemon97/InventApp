package com.uco.inventapp.service;

import java.util.ArrayList;

import com.uco.inventapp.domain.Client;
import com.uco.inventapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    public PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String first_name) throws UsernameNotFoundException {
        Client user = clientRepository.findByName(first_name);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + first_name);
        }
        return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(),
                new ArrayList<>());
    }

    public Client save(Client client) {
        client.setPassword(bcryptEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

}