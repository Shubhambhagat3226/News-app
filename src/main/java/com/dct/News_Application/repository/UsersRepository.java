package com.dct.News_Application.repository;

import com.dct.News_Application.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmailId(String email);

    boolean existsByEmailId(String emailId);

    Users findByUsername(String username);
}
