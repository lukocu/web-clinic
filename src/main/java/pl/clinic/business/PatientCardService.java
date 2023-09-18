package pl.clinic.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.clinic.api.dto.mapper.PatientsCardMapper;
import pl.clinic.business.dao.PatientCardRepository;
import pl.clinic.domain.Medications;
import pl.clinic.domain.PatientCard;
import pl.clinic.domain.Patients;
import pl.clinic.domain.Prescriptions;
import pl.clinic.domain.exception.NotFoundException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientCardService {

    private static final Logger logger = LoggerFactory.getLogger(PatientCardService.class);
    private PatientCardRepository patientCardRepository;
    private PatientsService patientsService;
    private PatientsCardMapper patientsCardMapper;
    private PrescriptionsService prescriptionsService;
    private MedicationsService medicationsService;
    @Transactional
    public PatientCard getMedicalPatientHistory(String pesel) {
        Patients patient = patientsService.searchPatient(pesel);

        return patientCardRepository.findPatientCardByPesel(patient.getPesel())
                .orElseThrow(() -> new NotFoundException("Patient medical History not found"));

    }

    @Transactional
    public void addPatientCardEntry(PatientCard patientCard) {
        Set<Medications> medicationsSet=new HashSet<>();


       patientCard.getPrescription().getMedications().forEach(
               medication -> {
                   Medications byName = medicationsService.findByName(medication.getMedicationName());
                    if(byName!=null){
                        medicationsSet.add(byName);
                    }
                    else{
                        medicationsSet.add(medication);
                    }
               }
       );

        logger.info("Zawartość medicationsSet przed: {}", medicationsSet);
        Prescriptions prescription = patientCard.getPrescription().withMedications(medicationsSet);

        PatientCard newPatientCard = patientCard.withPrescription(prescription);


        patientCardRepository.save(newPatientCard);

    }

// TODO
    public PatientCard getPatientCard(Integer patientId) {
        return null;

    }


}







