package com.dct.News_Application.service;

import com.dct.News_Application.dto.LoginDTO;
import com.dct.News_Application.dto.RegisterDTO;
import com.dct.News_Application.entity.Users;
import com.dct.News_Application.error.UserNotFoundException;
import com.dct.News_Application.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    private final UsersRepository userRepository;
    private final PasswordEncoder PASSWORD_ENCODER;
    private final JWTService jwtService;
    private final AuthenticationManager authManager;


    public UserServiceImpl(UsersRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.PASSWORD_ENCODER = passwordEncoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @Transactional
    public Users registerUser(RegisterDTO userDto) {


        System.out.println("Received user data: " + userDto);
        System.out.println("1");
        if (userRepository.existsByEmailId(userDto.getEmailId())) {
            throw new UserNotFoundException("User already Exist!!!");
        }
        System.out.println("2");
        if (!userDto.getConfirmPassword().equals(userDto.getNewPassword())) {
            throw new UserNotFoundException("Invalid password confirmation");
        }

        System.out.println("3");
        Users user = Users
                .builder()
                .username(userDto.getUsername())
                .roles("USER")
                .password(PASSWORD_ENCODER.encode(userDto.getConfirmPassword()))
                .emailId(userDto.getEmailId())
                .build();

        System.out.println(user);
        Users users = userRepository.save(user);
        System.out.println(users);
        return users;
    }

    @Override
    public boolean existsByEmail(String emailId) {
        return userRepository.existsByEmailId(emailId);
    }


    @Override
    public Users loginUser(LoginDTO userLogin) {

        Users user = findUserByEmail(userLogin.getEmailId());
        if (user == null) {
            throw new UserNotFoundException("User not exist");
        }
        if (!PASSWORD_ENCODER.matches(userLogin.getPassword(), user.getPassword())) {
            throw new UserNotFoundException("Password invalid!!");
        }

        return user;
    }

    @Override
    public Users findUserByEmail(String email) throws UserNotFoundException{
        return userRepository.findByEmailId(email).orElseThrow(() -> new UserNotFoundException("User not found!!!"));
    }

    @Override
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String verify(Users user) {
        try {
            Authentication authentication = authManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPassword()));

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getEmailId());
            }

            return "fail";
        } catch (Exception e) {
            return "failed";
        }
    }
}
