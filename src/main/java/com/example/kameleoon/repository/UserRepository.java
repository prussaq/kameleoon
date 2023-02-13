package com.example.kameleoon.repository;

import com.example.kameleoon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    @Query("select u.name from User u where u.id=:id")
    String getNameById(Long id);
}
