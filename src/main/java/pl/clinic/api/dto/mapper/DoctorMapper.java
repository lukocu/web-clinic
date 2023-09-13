package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.domain.Doctors;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    default DoctorDTO mapToDtoSpecAndOffices(final Doctors doctor) {
        return DoctorDTO.builder()
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .phone(doctor.getPhone())
                .pesel(doctor.getPesel())
                .specializationNames(doctor.getSpecializations().stream()
                        .map(SpecializationMapper.INSTANCE::mapToDto)
                        .collect(Collectors.toSet()))
                .offices(doctor.getOffices().stream()
                        .map(OfficeMapper.INSTANCE::mapToDtoForDoctor)
                        .collect(Collectors.toSet()))
                .build();

    }

    default Doctors mapFromDtoForPatientCard(DoctorDTO doctor) {
       return Doctors.builder()
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .phone(doctor.getPhone())
                .pesel(doctor.getPesel())
                .build();

    }

   default Doctors mapFromDtoSpecAndOffices(DoctorDTO doctor){
        return Doctors.builder().name(doctor.getName())
                .surname(doctor.getSurname())
                .phone(doctor.getPhone())
                .pesel(doctor.getPesel())
                .specializations(doctor.getSpecializationNames().stream()
                        .map(SpecializationMapper.INSTANCE::mapFromDto)
                        .collect(Collectors.toSet()))
                .offices(doctor.getOffices().stream()
                        .map(OfficeMapper.INSTANCE::mapFromDtoForDoctor)
                        .collect(Collectors.toSet()))
                .build();
    }
}
