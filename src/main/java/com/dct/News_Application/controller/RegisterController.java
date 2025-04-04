package com.dct.News_Application.controller;

import com.dct.News_Application.dto.RegisterDTO;
import com.dct.News_Application.entity.MyUserDetails;
import com.dct.News_Application.entity.Users;
import com.dct.News_Application.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "register"; // This should match your Thymeleaf template name
    }


    @PostMapping("/register")
    public String processRegister(@ModelAttribute RegisterDTO user,
                                  RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Register");

            // Check if email already exists
            if (userService.existsByEmail(user.getEmailId())) {
                redirectAttributes.addFlashAttribute("error", "User already exists!");
                return "redirect:/register";
            }

            // Password mismatch handling
            if (!user.getNewPassword().equals(user.getConfirmPassword())) {
                redirectAttributes.addFlashAttribute("error", "Passwords do not match!");
                return "redirect:/register";
            }

            Users users = userService.registerUser(user);


            // **Auto-login after registration**
            UserDetails userDetails = new MyUserDetails(users);
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            redirectAttributes.addFlashAttribute("success", "Registration successful! Please log in.");
            return "redirect:/home";
        } catch (Exception e) {
            System.out.println("Error");
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/register";
        }
    }

}
