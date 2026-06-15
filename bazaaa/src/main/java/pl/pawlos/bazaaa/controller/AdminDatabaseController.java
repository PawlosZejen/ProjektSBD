package pl.pawlos.bazaaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pawlos.bazaaa.service.AdminDatabaseService;

import java.util.Map;

@Controller
@RequestMapping("/admin/baza")
public class AdminDatabaseController {

    private final AdminDatabaseService adminDatabaseService;

    public AdminDatabaseController(AdminDatabaseService adminDatabaseService) {
        this.adminDatabaseService = adminDatabaseService;
    }

    @GetMapping
    public String listTables(Model model) {
        model.addAttribute("tables", adminDatabaseService.getTables());
        return "admin_database";
    }

    @GetMapping("/{tableName}")
    public String tableView(@PathVariable String tableName, Model model) {
        var columns = adminDatabaseService.getColumns(tableName);
        var rows = adminDatabaseService.getRows(tableName);
        var pk = adminDatabaseService.getPrimaryKey(tableName);

        model.addAttribute("tableName", tableName);
        model.addAttribute("columns", columns);
        model.addAttribute("rows", rows);
        model.addAttribute("primaryKey", pk);

        return "admin_table";
    }

    @PostMapping("/{tableName}/insert")
    public String insert(@PathVariable String tableName,
                         @RequestParam Map<String, String> params) {
        adminDatabaseService.insert(tableName, params);
        return "redirect:/admin/baza/" + tableName;
    }

    @PostMapping("/{tableName}/update/{id}")
    public String update(@PathVariable String tableName,
                         @PathVariable String id,
                         @RequestParam Map<String, String> params) {
        adminDatabaseService.update(tableName, id, params);
        return "redirect:/admin/baza/" + tableName;
    }

    @PostMapping("/{tableName}/delete/{id}")
    public String delete(@PathVariable String tableName,
                         @PathVariable String id) {
        adminDatabaseService.delete(tableName, id);
        return "redirect:/admin/baza/" + tableName;
    }
}
