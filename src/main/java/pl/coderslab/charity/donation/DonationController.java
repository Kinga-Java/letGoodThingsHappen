package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DonationController {
    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionService.getAll();
    }

    @GetMapping("donation")
    public String addDonation(Model model) {
    /*    model.addAttribute("donation", new Donation());*/
        return "donation/form";
    }

/*    @PostMapping("/donation")
    public String saveDonation(@Valid @ModelAttribute("donation") Donation donation, BindingResult result) {
        if (result.hasErrors()) {
            return "donation/form";
        }
        donationService.saveDonation(donation);
        return "donation/form-confirmation";
    }*/
}
