package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static pl.coderslab.charity.user.Role.ROLE_ADMIN;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;
    private final InstitutionService institutionService;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    @ModelAttribute("users")
    public List<User> getAllUsers(){
        return userService.getAll();
    }


    @RequestMapping("/createadmin")
    public String createUserAdmin(){
        userService.createFirstUserAdmin();
        return "redirect:/login";
    }

        @GetMapping("/home")
    public String usersList(Model model){
            model.addAttribute("institutions", institutionService.getAll());
            model.addAttribute("donationQuantitySum", donationRepository.donationQuantitiesSum());
            model.addAttribute("donationQuantity", donationRepository.count());
            return "admin/admin";

    }

    @GetMapping("/addAdmin")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/addAdmin";
    }

    @PostMapping("/addAdmin")
    public String registerUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addAdmin";
        }
        userService.createAdmin(user);
        return "redirect:/admin/list";
    }

    @GetMapping("/list")
    public String getAllAdmins(Model model) {
        model.addAttribute("admins", userService.getAllAdmins(Role.ROLE_ADMIN.toString()));
        return "admin/list";
    }


    @GetMapping("/edit/{id}")
    public String editAdmin(Model model, @PathVariable long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            return "error";
        }
        model.addAttribute("user", user);
        return "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String editAdmin(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/edit";
        }
        userService.createAdmin(user);
        return "redirect:/admin/list";
    }



    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable long id, Model model) {
        User user = userService.findUserByIdAndRole(id, Role.ROLE_ADMIN.toString());
        if (user == null) {
            return "error";
        }
        model.addAttribute("user", user);
        return "admin/delete";
    }

    @PostMapping("/delete")
    public String deleteAdminById(@RequestParam long id){
        User user = userService.findUserByIdAndRole(id, Role.ROLE_ADMIN.toString());
        userService.deleteAdminById(id);
        return "redirect:/admin/list";
    }



}

