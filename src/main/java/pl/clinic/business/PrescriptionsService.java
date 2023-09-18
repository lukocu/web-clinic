package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.PrescriptionsRepository;
import pl.clinic.domain.Prescriptions;
import pl.clinic.domain.exception.NotFoundException;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class PrescriptionsService {
    private PrescriptionsRepository prescriptionsRepository;
    private MedicationsService medicationsService;

    @Transactional
    public void save(Prescriptions prescription) {

        prescriptionsRepository.save(prescription);
    }

    public Prescriptions findByDate(OffsetDateTime prescriptionDate) {
            return prescriptionsRepository.findByDate(prescriptionDate)
                    .orElseThrow(()-> new NotFoundException("prescription not found"));
    }
}
