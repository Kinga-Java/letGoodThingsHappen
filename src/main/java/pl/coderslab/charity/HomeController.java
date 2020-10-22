package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.InstitutionService;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationRepository donationRepository;

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionService.getAll());
        model.addAttribute("donationQuantitySum", donationRepository.donationQuantitiesSum());
        model.addAttribute("donationQuantity", donationRepository.count());
        return "index";
    }
}
