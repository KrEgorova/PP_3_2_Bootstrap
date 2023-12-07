package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String printUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userService.showUserById(user.getId()));
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("userRoles", user.getRoles().toString().replaceAll("[\\[\\]]", "").replaceAll("ROLE_", ""));
        model.addAttribute("hasRoleAdmin", roleService.hasAdminRole(user));
        return "user";
    }

    @GetMapping("/index")
    public String printIndex() {
        return "index";
    }
}
