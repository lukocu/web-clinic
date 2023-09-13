package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.clinic.api.dto.*;
import pl.clinic.api.dto.mapper.PatientsCardMapper;
import pl.clinic.business.dao.PatientCardRepository;
import pl.clinic.domain.*;
import pl.clinic.domain.exception.NotFoundException;

@Service
@AllArgsConstructor
public class PatientCardService {


    private PatientCardRepository patientCardRepository;
    private PatientsService patientsService;
    private PatientsCardMapper patientsCardMapper;
    @Transactional
    public PatientCard getMedicalPatientHistory(String pesel) {
        Patients patient = patientsService.searchPatient(pesel);

        return patientCardRepository.findPatientCardByPesel(patient.getPesel())
                .orElseThrow(() -> new NotFoundException("Patient medical History not found"));

    }

    @Transactional
    public void addPatientCardEntry(PatientCard patientCard) {

        // Utwórz nowy wpis na podstawie pól z formularza
        PatientCard newEntry = PatientCard.builder()
                .diagnosisDate(patientCard.getDiagnosisDate())
                .diagnosisNote(patientCard.getDiagnosisNote())
                .doctor(patientCard.getDoctor())
                .diseases(patientCard.getDiseases())
                .prescription(patientCard.getPrescription())
                .build();

        // Dodaj nowy wpis do istniejącej karty pacjenta

        patientCardRepository.save(newEntry);
    }

// TODO
    public PatientCard getPatientCard(Integer patientId) {
        return null;

    }

    public void saveCard(PatientCardDTO patientCardDTO) {
        patientCardRepository.save(patientsCardMapper.mapFromDto(patientCardDTO));
    }
}
















