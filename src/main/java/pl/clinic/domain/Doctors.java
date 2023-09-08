package pl.clinic.domain;

import lombok.*;
import pl.clinic.infrastructure.database.entity.OfficeEntity;

import java.util.Set;

@With
@Value
@EqualsAndHashCode(of = {"doctorId", "pesel"})
@ToString(of = {"doctorId", "name", "surname", "pesel", "phone"})
@Builder
public class Doctors extends User{

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