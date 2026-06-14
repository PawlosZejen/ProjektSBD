package pl.pawlos.bazaaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pawlos.bazaaa.model.Kurs;
import pl.pawlos.bazaaa.model.Kursant;
import pl.pawlos.bazaaa.model.User;
import pl.pawlos.bazaaa.service.KursantService;
import pl.pawlos.bazaaa.service.RoleService;
import pl.pawlos.bazaaa.service.UserService;
import pl.pawlos.bazaaa.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/kursanci")
public class KursantController {

    private final KursantService kursantService;

    public KursantController(KursantService kursantService) {
        this.kursantService = kursantService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("kursanci", kursantService.getAll());
        return "kursanci";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("kursant", new Kursant());
        return "kursant_form";
    }

    @PostMapping("/save")
    public String save(Kursant kursant) {
        kursantService.save(kursant);
        return "redirect:/kursanci";
    }

    @GetMapping("/delete/{pesel}")
    public String delete(@PathVariable String pesel) {
        kursantService.delete(pesel);
        return "redirect:/kursanci";
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        String email = userDetails.getUser().getEmail();

        Kursant kursant = kursantService.getByEmail(email);
        model.addAttribute("kursant", kursant);
        model.addAttribute("user", user);
        return "kursant_profile";
    }

    @GetMapping("/edit/{pesel}")
    public String edit(@PathVariable String pesel, Model model) {
        Kursant kursant = kursantService.getByPesel(pesel);
        model.addAttribute("kursant", kursant);
        return "kursant_edit";
    }

    // Save the updated data
    @PostMapping("/update")
    public String update(@ModelAttribute Kursant kursant) {
        kursantService.update(kursant);
        return "redirect:/kursanci";
    }
}
