package com.mikro.gsmikro.security;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * from user WHERE emailAddress = :emailAddress", nativeQuery = true)
    Optional<User> findByEmailAddress(String emailAddress);

}
