package org.example.web.controllers;


import jakarta.validation.Valid;
import org.example.web.DTO.UserRegistrationDTO;
import org.example.web.models.UserEntity;
import org.example.web.services.AuthService;
import org.example.web.services.UserEntityService;
import org.example.web.views.UserProfileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class AuthController {

    private AuthService authService;

    private UserEntityService userEntityService;

    @Autowired
    public AuthController(AuthService authService, UserEntityService userEntityService) {
        this.authService = authService;
        this.userEntityService = userEntityService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initForm() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDTO userRegistrationDTO,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRegistrationDTO", userRegistrationDTO);
            return "register";
        }

        if (!authService.isUsernameUnique(userRegistrationDTO.getUsername())) {
            model.addAttribute("usernameError", "Username is already taken");
            model.addAttribute("userRegistrationDTO", userRegistrationDTO);
            return "register";
        }

        if (!authService.isEmailUnique(userRegistrationDTO.getEmail())) {
            model.addAttribute("emailError", "Email is already taken");
            model.addAttribute("userRegistrationDTO", userRegistrationDTO);
            return "register";
        }

        this.authService.register(userRegistrationDTO);

        return "redirect:/users/login";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        UserEntity userEntity = authService.getUser(username);

        UserProfileView userProfileView = new UserProfileView(
                username,
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getAge()
        );

        model.addAttribute("user", userProfileView);

        return "profile";
    }
}
