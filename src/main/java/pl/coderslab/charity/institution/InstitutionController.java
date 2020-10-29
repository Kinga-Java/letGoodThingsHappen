package pl.coderslab.charity.institution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/institution")
public class InstitutionController {

    private final InstitutionService institutionService;



    @GetMapping("/add")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        return "institution/form";
    }

    @GetMapping("list")
    public String getAllInstitutions(Model model) {
        model.addAttribute("institutions", institutionService.getAll());
        return "institution/list";
    }

    @PostMapping("/add")
    public String saveInstitution(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "institution/form";
        }
        institutionService.addInstitution(institution);
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/{id}/edit")
    public String editInstitution(@PathVariable long id, Model model) {
        Institution institution = institutionService.findInstitutionById(id);
        if (institution == null) {
            return "error";
        }
        model.addAttribute("institution", institution);
        return "institution/edit";
    }

    @PostMapping("/{id}/edit")
    public String saveEditedInstitution(@PathVariable long id, @Valid Institution institution, BindingResult result) {
        if (institution.getId() != id) {
            return "error";
        }
        if (result.hasErrors()) {
            return "institution/edit";
        }
        institutionService.updateInstitution(institution);
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstitution(@PathVariable long id, Model model) {
        Institution institution = institutionService.findInstitutionById(id);
        if (institution == null) {
            return "error";
        }
        model.addAttribute("institution", institution);
        return "institution/delete";
    }

    @PostMapping("/delete")
    public String deleteInstitutionById(@RequestParam long id){
        Institution institution = institutionService.findInstitutionById(id);
        institutionService.deleteInstitutionById(id);
        return "redirect:/admin/institution/list";
    }


}
