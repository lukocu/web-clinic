package pl.clinic.api.dto;

import lombok.*;
import pl.clinic.domain.Office;
import pl.clinic.domain.PatientCard;
import pl.clinic.domain.Specialization;
import pl.clinic.domain.User;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    Integer doctorId;

    String name;

    String surname;

    String phone;

    String pesel;

    Set<SpecializationDTO> specializationNames;

    Set<OfficeDTO> offices;

    Set<PatientCardDTO> patientCards;
}
