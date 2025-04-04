package com.dct.News_Application.controller;

import com.dct.News_Application.dto.LoginDTO;
import com.dct.News_Application.entity.Users;
import com.dct.News_Application.service.JWTService;
import com.dct.News_Application.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final UserService userService;
    private final JWTService jwtService;

    public LoginController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "index";
    }

    @PostMapping("/perform-login")
    public String processLogin(@RequestParam("username") String emailId,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Perform login");
            LoginDTO loginDTO = new LoginDTO(emailId, password);
            Users user = userService.loginUser(loginDTO);
            String token = jwtService.generateToken(user.getEmailId());
            System.out.println("Token: " + token);
            redirectAttributes.addFlashAttribute("success", "Login successful! Your token: " + token);
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Invalid credentials");
            return "redirect:/login";
        }
    }

}
