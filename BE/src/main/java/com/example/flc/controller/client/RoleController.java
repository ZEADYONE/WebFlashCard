package com.example.flc.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.flc.domain.Role;
import com.example.flc.service.RoleService;

@Controller
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/role")
    public String viewRole(Model model) {
        model.addAttribute("role", new Role()); // phải có dòng này
        model.addAttribute("roles", this.roleService.getAll());
        return "admin/role/role";
    }

    @PostMapping("/admin/role/create")
    public String createRole(@ModelAttribute("role") Role role) {
        this.roleService.handleSave(role);
        return "redirect:/admin/role";
    }
}
