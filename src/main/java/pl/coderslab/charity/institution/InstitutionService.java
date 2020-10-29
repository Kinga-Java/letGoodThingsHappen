package pl.coderslab.charity.institution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public List<Institution> getAll() {
        return institutionRepository.findAll();
    }
    public Institution findInstitutionById(long id){
        return institutionRepository.findById(id);
    }

    public Institution addInstitution(Institution institution){
        return institutionRepository.save(institution);
    }

    public void updateInstitution(Institution institution){
        institutionRepository.save(institution);
    }

    public void deleteInstitution(Institution institution){
        institutionRepository.delete(institution);
    }
    public void deleteInstitutionById(long id){
        institutionRepository.deleteById(id);
    }
}
