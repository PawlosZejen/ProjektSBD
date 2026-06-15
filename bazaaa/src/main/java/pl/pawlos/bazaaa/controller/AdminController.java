package pl.pawlos.bazaaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pawlos.bazaaa.service.AdminService;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/tabele")
    public String pokazTabele(Model model) {
        model.addAttribute("tabele", adminService.getAllTables());
        return "admin_panel";
    }

    @GetMapping("/tabela/{nazwa}")
    public String pokazDane(@PathVariable String nazwa, Model model) {
        model.addAttribute("nazwa", nazwa);
        model.addAttribute("kolumny", adminService.getColumns(nazwa));
        model.addAttribute("wiersze", adminService.getRows(nazwa));
        return "admin_tabela";
    }

    @PostMapping("/tabela/{nazwa}/insert")
    public String insert(@PathVariable String nazwa, @RequestParam Map<String, String> dane) {
        adminService.insertRow(nazwa, dane);
        return "redirect:/admin/tabela/" + nazwa;
    }

    @PostMapping("/tabela/{nazwa}/update/{id}")
    public String update(@PathVariable String nazwa, @PathVariable Long id, @RequestParam Map<String, String> dane) {
        adminService.updateRow(nazwa, id, dane);
        return "redirect:/admin/tabela/" + nazwa;
    }

    @PostMapping("/tabela/{nazwa}/delete/{id}")
    public String delete(@PathVariable String nazwa, @PathVariable Long id) {
        adminService.deleteRow(nazwa, id);
        return "redirect:/admin/tabela/" + nazwa;
    }
}
