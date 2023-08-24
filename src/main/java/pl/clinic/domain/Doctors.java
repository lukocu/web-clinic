package pl.clinic.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = "doctorId")
@ToString(of = {"doctorId","name","surname","pesel","phone"})
@Builder
public class Doctors {

     Integer doctorId;

     String name;

     String surname;

     String phone;

     String pesel;

     Set<Specialization> specializations;

     User user;

     Set<Office> offices;

     Set<PatientCard> patientCards;
}