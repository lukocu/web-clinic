package pl.clinic.api.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientCardDTO {

    Integer patientCardId;

    OffsetDateTime diagnosisDate;

    String diagnosisNote;

    PatientsDTO patient;


    DoctorDTO doctor;

    Set<DiseasesDTO> diseases;

    PrescriptionsDTO prescription;


}