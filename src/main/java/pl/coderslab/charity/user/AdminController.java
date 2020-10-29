package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.InstitutionService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final InstitutionService institutionService;
    private final DonationRepository donationRepository;

    @ModelAttribute("users")
    public List<User> getAllUsers(){
        return userService.getAll();
    }


    @RequestMapping("/createadmin")
    public String createUserAdmin(){
        userService.createUserAdmin();
        return "user/login";
    }

        @GetMapping("/admin/home")
    public String usersList(Model model){
            model.addAttribute("institutions", institutionService.getAll());
            model.addAttribute("donationQuantitySum", donationRepository.donationQuantitiesSum());
            model.addAttribute("donationQuantity", donationRepository.count());
            return "admin/admin";

    }
}

