package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.MedicationsRepository;
import pl.clinic.domain.Medications;

import java.util.Set;

@Service
@AllArgsConstructor
public class MedicationsService {
    MedicationsRepository medicationsRepository;

    @Transactional
    public void save(Set<Medications> medicationsSet) {

        for (Medications medication : medicationsSet) {
            String medicationName = medication.getMedicationName();


            Medications existingMedication = medicationsRepository.findByNameNoOptional(medicationName);

            if (existingMedication == null)  {

                medicationsRepository.save(medication);
            }
        }
    }

}
