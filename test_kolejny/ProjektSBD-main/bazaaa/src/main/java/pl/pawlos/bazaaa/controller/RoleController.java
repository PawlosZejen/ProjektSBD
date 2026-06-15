package pl.pawlos.bazaaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pawlos.bazaaa.model.Role;
import pl.pawlos.bazaaa.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("roles", roleService.getAll());
        return "roles";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("role", new Role());
        return "role_form";
    }

    @PostMapping("/save")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/roles";
    }
}
