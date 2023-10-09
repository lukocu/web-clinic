package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.PrescriptionsRepository;
import pl.clinic.domain.Prescriptions;

@Service
@AllArgsConstructor
public class PrescriptionsService {
    private PrescriptionsRepository prescriptionsRepository;


    @Transactional
    public void save(Prescriptions prescription) {

        prescriptionsRepository.save(prescription);
    }

@Transactional
    public void deletePrescription(Prescriptions prescription) {
    prescriptionsRepository.delete(prescription);
    }
}
