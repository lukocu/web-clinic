package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.clinic.api.dto.mapper.PatientsCardMapper;
import pl.clinic.business.dao.PatientCardRepository;
import pl.clinic.domain.PatientCard;
import pl.clinic.domain.Patients;
import pl.clinic.domain.Prescriptions;
import pl.clinic.domain.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientCardService {


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


}







