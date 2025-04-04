package com.dct.News_Application.service;

import com.dct.News_Application.dto.LoginDTO;
import com.dct.News_Application.dto.RegisterDTO;
import com.dct.News_Application.entity.Users;

import java.util.List;

public interface UserService {

    Users loginUser(LoginDTO userLogin);

    Users findUserByEmail(String email);

    List<Users> findAllUsers();

    Users registerUser(RegisterDTO user);

    boolean existsByEmail(String emailId);

    String verify(Users user);

}
