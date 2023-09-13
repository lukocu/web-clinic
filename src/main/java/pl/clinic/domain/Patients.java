package pl.clinic.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = {"patientId","pesel"})
@ToString(of = {"patientId", "name", "pesel", "birthDate", "address", "phone"})
@Builder
public class Patients {

    Integer patientId;

    String name;

    String surname;

    String pesel;

    LocalDate birthDate;

    String address;

    String phone;

    User user;

    Set<Appointments> appointments;

    Set<PatientCard> patientCard;
}