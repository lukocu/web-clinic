package pl.clinic.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = {"patientId", "pesel"})
@ToString(of = {"patientId", "name", "pesel", "birthDate", "address", "phone"})
@Builder
public class Patients extends User{

    Integer patientId;

    String name;

    String surname;

    String pesel;

    LocalDate birthDate;

    String address;

    String phone;

    Set<Appointments> appointments;

}