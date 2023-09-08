package pl.clinic.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.clinic.business.dao.PatientsRepository;
import pl.clinic.domain.Patients;
import pl.clinic.domain.exception.NotFoundException;

@Service
@AllArgsConstructor
public class PatientsService {

    private PatientsRepository patientsRepository;

    @Transactional
    public Patients searchPatient(String patientPesel) {
        Patients patient = patientsRepository.findPatientByPesel(patientPesel)
                .orElseThrow(() -> new NotFoundException("Patient not found"));

        return patient;
    }


}
