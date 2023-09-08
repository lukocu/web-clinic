package pl.clinic.domain;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = "patientCardId")
@ToString(of = {"patientCardId", "diagnosisDate", "diagnosisNote"})
@Builder
public class PatientCard {

    Integer patientCardId;

    OffsetDateTime diagnosisDate;

    String diagnosisNote;

    Patients patient;


    Doctors doctor;

    Set<Diseases> diseases;

    Prescriptions prescription;


}