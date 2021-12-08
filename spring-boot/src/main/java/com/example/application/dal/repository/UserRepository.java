package com.example.application.dal.repository;

import com.example.application.dal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(
            value = "SELECT * FROM users WHERE users.role = ?1",
            nativeQuery = true)
    List<User> getUsersByRole(String role);

    @Query(
            value = "SELECT * FROM users WHERE users.email = ?1",
            nativeQuery = true)
    User getByEmail(String email);


}