package com.Camera.access.controller;

import com.Camera.access.model.User;
import com.Camera.access.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class PageController {

    @Autowired
    private UserRepository userRepository;

    // ✅ FIXED: Root path mapping add ki hai taaki Whitelabel Error na aaye
    @GetMapping("/")
    public String home(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }

    // 1. Login Page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 2. Register Page
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // 3. Central Dashboard Redirector
    @GetMapping("/dashboard")
    public String dashboard(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }

        // Role check logic
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/user/dashboard";
    }

    // 4. User Specific Dashboard
    @GetMapping("/user/dashboard")
    public String userDashboard(Authentication auth, Model model) {
        String username = auth.getName();
        Optional<User> user = userRepository.findByUsername(username);

        user.ifPresent(u -> model.addAttribute("name", u.getFullName()));
        return "user-dashboard";
    }

    // 5. Admin Specific Dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Authentication auth, Model model) {
        model.addAttribute("adminName", auth.getName());
        return "admin-dashboard";
    }
}