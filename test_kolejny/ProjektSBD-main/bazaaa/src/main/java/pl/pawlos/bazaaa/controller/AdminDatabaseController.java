package pl.pawlos.bazaaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawlos.bazaaa.service.AdminDatabaseService;

@Controller
@RequestMapping("/admin/baza")
public class AdminDatabaseController {

    private final AdminDatabaseService adminDatabaseService;

    public AdminDatabaseController(AdminDatabaseService adminDatabaseService) {
        this.adminDatabaseService = adminDatabaseService;
    }

    @GetMapping
    public String tables(Model model) {
        model.addAttribute("tables", adminDatabaseService.getTables());
        return "admin_database";
    }

    @GetMapping("/{tableName}")
    public String table(@PathVariable String tableName, Model model) {
        model.addAttribute("tableName", tableName);
        model.addAttribute("columns", adminDatabaseService.getColumns(tableName));
        model.addAttribute("rows", adminDatabaseService.getRows(tableName));
        return "admin_table";
    }
}
