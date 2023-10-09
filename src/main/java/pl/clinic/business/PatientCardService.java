package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.clinic.business.dao.PatientCardRepository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.PatientCard;
import pl.clinic.domain.Patients;
import pl.clinic.domain.exception.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientCardService {
@Lazy
    private  PatientsService patientService;
    private DoctorsService doctorsService;
    private PatientCardRepository patientCardRepository;


    @Transactional
    public void addPatientCardEntry(PatientCard patientCard) {

        patientCardRepository.save(patientCard);

    }

    @Transactional
    public List<PatientCard> getPatientCards(Integer patientId) {


        return patientCardRepository.findPatientCardByPatientId(patientId);
    }
    @Transactional
    public PatientCard savePatientCard(PatientCard patientCard) {

        Patients patients =patientService.getPatient(patientCard.getPatient().getPatientId());
        Doctors doctor=doctorsService.getDoctor(patientCard.getDoctor().getDoctorId());

        PatientCard newPatientCard =
                PatientCard.builder()
                        .patient(patients)
                        .doctor(doctor)
                        .diagnosisDate(patientCard.getDiagnosisDate())
                        .diagnosisNote(patientCard.getDiagnosisNote())
                        .prescription(patientCard.getPrescription())
                        .diseases(patientCard.getDiseases())
                        .build();

       return   patientCardRepository.save(newPatientCard);
    }

    @Transactional
    public void deletePatientCard(Integer patientCardId) {
        patientCardRepository.deleteById(patientCardId);
    }

    @Transactional
    public PatientCard findPatientCard(Integer patientCardId) {
        return patientCardRepository.findPatientCard(patientCardId)
                .orElseThrow(() -> new NotFoundException("Patient Card not found"));
    }
}







