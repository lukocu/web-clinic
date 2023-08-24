package pl.clinic.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = "patientId")
@ToString(of = {"patientId", "name", "birthDate", "address", "phone"})
@Builder
public class Patients {

    Integer patientId;

    String name;

    String surname;

    LocalDate birthDate;

    String address;

    String phone;

    User user;

    Set<Appointments> appointments;

}