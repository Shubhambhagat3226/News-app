package com.dct.News_Application.security;

import com.dct.News_Application.entity.MyUserDetails;
import com.dct.News_Application.entity.Users;
import com.dct.News_Application.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepository userRepository;

    public MyUserDetailsService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Hello");
        Users user = userRepository.findByEmailId(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new MyUserDetails(user);
    }
}

