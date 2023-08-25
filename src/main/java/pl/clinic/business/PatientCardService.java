package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.business.dao.PatientCardRepository;
import pl.clinic.domain.PatientCard;
import pl.clinic.domain.Patients;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientCardService {
    private PatientCardRepository patientCardRepository;

    public List<PatientCard> getMedicalHistoryForPatient(Patients patient) {
        return patientCardRepository.findByPatient(patient);
    }
//tutaj zmien na pesel


}
