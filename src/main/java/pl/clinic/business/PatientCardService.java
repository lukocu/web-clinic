package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.clinic.business.dao.PatientCardRepository;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.PatientCard;
import pl.clinic.domain.Patients;
import pl.clinic.domain.Prescriptions;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientCardService {

    private  PatientsService patientService;
    private DoctorsService doctorsService;
    private PatientCardRepository patientCardRepository;
    private PrescriptionsService prescriptionsService;

    @Transactional
    public void addPatientCardEntry(PatientCard patientCard) {

        patientCardRepository.save(patientCard);

    }

    @Transactional
    public List<PatientCard> getPatientCards(Integer patientId) {
        List<PatientCard> patientCards = patientCardRepository.findPatientCardByPatientId(patientId);

        OffsetDateTime now = OffsetDateTime.now();
    List<Prescriptions> newPrescriptions= new ArrayList<>();
        for (PatientCard patientCard : patientCards) {
            Prescriptions prescription = patientCard.getPrescription();
                if (prescription.getPrescriptionDateEnd().isBefore(now)) {
                   newPrescriptions.add(prescription.withPrescriptionAvailable(false));
                }
        }
        newPrescriptions.forEach(prescriptions -> prescriptionsService.save(prescriptions));

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

}







