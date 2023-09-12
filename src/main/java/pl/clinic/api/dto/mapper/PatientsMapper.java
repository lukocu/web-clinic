package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.domain.Patients;

@Mapper(componentModel = "spring")
public interface PatientsMapper {

    PatientsMapper INSTANCE = Mappers.getMapper(PatientsMapper.class);
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

   default PatientsDTO mapToDtoWithoutAppointment(Patients patient) {
        return PatientsDTO.builder()
                .name(patient.getName())
                .surname(patient.getSurname())
                .pesel(patient.getPesel())
                .birthDate(patient.getBirthDate())
                .address(patient.getAddress())
                .phone(patient.getPhone())
                .build();


    }
}
