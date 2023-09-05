package pl.clinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientsDTO {


    private String name;
    private String surname;
    private String pesel;
    private LocalDate birthDate;
    private String address;
    private String phone;

    private Set<AppointmentsDTO> appointment; // Lista ID wizyt pacjenta
}
