package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Specialization;

import java.util.Set;
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
    default Doctors mapFromDtoSpecAndOffices(final DoctorDTO doctorDTO) {
        return Doctors.builder()
                .name(doctorDTO.getName())
                .surname(doctorDTO.getSurname())
                .phone(doctorDTO.getPhone())
                .pesel(doctorDTO.getPesel())
                .specializations(doctorDTO.getSpecializationNames().stream()
                        .map(SpecializationMapper.INSTANCE::mapFromDto)
                        .collect(Collectors.toSet()))
                .offices(doctorDTO.getOffices().stream()
                        .map(OfficeMapper.INSTANCE::mapFromDto)
                        .collect(Collectors.toSet()))
                .build();

    }
 default DoctorDTO mapToDto(final Doctors doctor) {
        return DoctorDTO.builder()
                .doctorId(doctor.getDoctorId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .phone(doctor.getPhone())
                .pesel(doctor.getPesel())
                .build();

    } default Doctors mapFromDto(final DoctorDTO doctor) {
        return Doctors.builder()
                .doctorId(doctor.getDoctorId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .phone(doctor.getPhone())
                .pesel(doctor.getPesel())
                .build();

    }

    default Doctors mapFromDtoForPatientCard(DoctorDTO doctor) {
       return Doctors.builder()
               .doctorId(doctor.getDoctorId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .phone(doctor.getPhone())
                .pesel(doctor.getPesel())
                .build();

    }

   default Doctors mapFromDtoSpec(DoctorDTO doctor){
        return Doctors.builder().name(doctor.getName())
                .surname(doctor.getSurname())
                .phone(doctor.getPhone())
                .pesel(doctor.getPesel())
                .specializations(doctor.getSpecializationNames().stream()
                        .map(SpecializationMapper.INSTANCE::mapFromDto)
                        .collect(Collectors.toSet()))
                .build();
    }



}
