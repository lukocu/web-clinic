package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.business.dao.PatientCardRepository;
import pl.clinic.domain.PatientCard;
import pl.clinic.domain.Patients;
import pl.clinic.domain.exception.NotFoundException;

@Service
@AllArgsConstructor
public class PatientCardService {


    private PatientCardRepository patientCardRepository;
    private PatientsService patientsService;

    @Transactional
    public PatientCard getMedicalPatientHistory(String pesel) {
        Patients patient = patientsService.searchPatient(pesel);

        return patientCardRepository.findPatientCardById(patient.getPatientId())
                .orElseThrow(() -> new NotFoundException("Patient medical History not found"));

    }

    @Transactional
    public void addPatientCardEntry(PatientCard patientCard) {

        Patients patient = patientsService.searchPatient(patientCard.getPatient().getPesel());

        PatientCard existingPatientCard = patientCardRepository.findPatientCardById(patient.getPatientId())
                .orElseThrow(() -> new NotFoundException("Patient's medical history not found"));

        // Utwórz nowy wpis na podstawie pól z formularza
        PatientCard newEntry = PatientCard.builder()
                .diagnosisDate(patientCard.getDiagnosisDate())
                .diagnosisNote(patientCard.getDiagnosisNote())
                .doctor(patientCard.getDoctor())
                .diseases(patientCard.getDiseases())
                .prescription(patientCard.getPrescription())
                .build();

        // Dodaj nowy wpis do istniejącej karty pacjenta

        patientCardRepository.save(existingPatientCard);
    }


}
