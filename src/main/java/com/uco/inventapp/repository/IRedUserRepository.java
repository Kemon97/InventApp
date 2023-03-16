package com.uco.inventapp.repository;


import com.uco.inventapp.domain.RedUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface IRedUserRepository extends ReactiveCrudRepository<RedUser, String> {

    @Query("{ 'username': ?0 }")
    RedUser findByUsername(String username);
}