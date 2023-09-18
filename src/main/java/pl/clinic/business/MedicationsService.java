package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.MedicationsRepository;
import pl.clinic.domain.Medications;
import pl.clinic.domain.exception.NotFoundException;

import java.util.Set;

@Service
@AllArgsConstructor
public class MedicationsService {
    MedicationsRepository medicationsRepository;

    @Transactional
    public void save(Set<Medications> medicationsSet) {

        for (Medications medication : medicationsSet) {
            String medicationName = medication.getMedicationName();

            // Sprawdź, czy lek o danej nazwie istnieje w bazie danych
            Medications existingMedication = medicationsRepository.findByNameNoOptional(medicationName);

            if (existingMedication == null)  {
                // Lek o danej nazwie nie istnieje, możesz go zapisać.
                medicationsRepository.save(medication);
            }
        }


    }


    public Medications findByName(String medicationName) {
      return   medicationsRepository.findByName(medicationName)
              .orElse(null);
    }
}
