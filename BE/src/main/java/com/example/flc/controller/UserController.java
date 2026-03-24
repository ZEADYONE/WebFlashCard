package com.example.flc.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.flc.domain.User;
import com.example.flc.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/admin/user")
    public String getHomePage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("listUser", users);
        return "admin/user/homepage";
    }

    // DETAIL USER
    @GetMapping("/admin/user/view/{id}")
    public String viewUser(@PathVariable("id") long id, Model model) {
        User user = userService.getUserDetail(id);
        model.addAttribute("user", user);

        return "admin/user/view";
    }

    // CREATE USER
    @RequestMapping("admin/user/create")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        // List<Role> roles = roleService.getAllRoles();
        // model.addAttribute("roles", roles);
        // Ben homepage
        return "admin/user/create";
    }

    @RequestMapping(value = "admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") @Valid User newUser,
            BindingResult bindingResult) {

        // ERROR VALID
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + "=>>>>>>>>>>>>>>>>>>>>>>>>> " + error.getDefaultMessage());
        }

        if (bindingResult.hasErrors()) {
            return "admin/user/create";
        }

        // HASH CODE
        String hassPassWord = this.passwordEncoder.encode(newUser.getPassWord());
        newUser.setPassWord(hassPassWord);

        newUser.setRole(this.userService.getRoleByName(newUser.getRole().getName()));
        newUser.setStatus(true);
        this.userService.handelSaveUser(newUser);
        return "redirect:/admin/user";
    }

    // UPATE USER

    @GetMapping("/admin/user/update/{id}")
    public String getUserUpdate(@PathVariable("id") long id, Model model) {
        User user = userService.getUserDetail(id);

        model.addAttribute("newUser", user);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(@ModelAttribute("newUser") User user) {
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        this.userService.handelSaveUser(user);
        return "redirect:/admin/user";
    }

    // STATUS
    @PostMapping("/admin/user/status/{id}")
    public String setUserStatus(@PathVariable("id") long id, Model model) {
        this.userService.setUserStatus(id);
        return "redirect:/admin/user";
    }

}
