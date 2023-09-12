package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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


    String name;

    String surname;

    String phone;

    String pesel;

    String specializationNames;

    Set<Integer> offices;

     Set<PatientCardDTO> patientCards;
}
