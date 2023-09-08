package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.Patients;

import java.time.LocalDate;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PatientsMapper {
    Patients mapFromDto(PatientsDTO patientsDTO);

    default Patients mapFromDtoWithoutAppointment(PatientsDTO patientsDTO) {
        return Patients.builder()
                .name(patientsDTO.getName())
                .surname(patientsDTO.getSurname())
                .pesel(patientsDTO.getPesel())
                .birthDate(patientsDTO.getBirthDate())
                .address(patientsDTO.getAddress())
                .phone(patientsDTO.getPhone())
                .appointments(null)
                .build();
    }

    ;


}
