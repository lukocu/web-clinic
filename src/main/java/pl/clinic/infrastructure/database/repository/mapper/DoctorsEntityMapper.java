package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Doctors;
import pl.clinic.infrastructure.database.entity.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorsEntityMapper {


    DoctorsEntityMapper INSTANCE = Mappers.getMapper(DoctorsEntityMapper.class);

    @Mapping(target = "specializations", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "patientCards", ignore = true)
    @Mapping(target = "offices", ignore = true)
    Doctors mapFromEntity(DoctorsEntity entity);


    default Doctors mapFromEntityWithSpecializationsAndOffices(DoctorsEntity entity) {
        return mapFromEntity(entity)
                .withSpecializations(entity.getSpecializations().stream()
                        .map(SpecializationEntityMapper.INSTANCE::mapFromEntity)
                        .collect(Collectors.toSet()))
                .withOffices(entity.getOffices().stream()
                        .map(OfficeEntityMapper.INSTANCE::mapFromEntity)
                        .collect(Collectors.toSet()));
    }

    default Doctors mapFromEntityWithAllFields(DoctorsEntity entity) {
        return mapFromEntity(entity)
                .withSpecializations(entity.getSpecializations().stream()
                        .map(SpecializationEntityMapper.INSTANCE::mapFromEntity)
                        .collect(Collectors.toSet()))
                .withOffices(entity.getOffices().stream()
                        .map(OfficeEntityMapper.INSTANCE::mapFromEntityWithoutDoctor)
                        .collect(Collectors.toSet()))
                .withPatientCards(entity.getPatientCards().stream()
                        .map(PatientCardEntityMapper.INSTANCE::mapFromEntityWithFieldsForDoc)
                        .collect(Collectors.toSet()));
    }

    default DoctorsEntity mapToEntity(Doctors doctor) {
        return DoctorsEntity.builder()
                .doctorId(doctor.getDoctorId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .pesel(doctor.getPesel())
                .phone(doctor.getPhone())
                .specializations(doctor.getSpecializations().stream()
                        .map(SpecializationEntityMapper.INSTANCE::mapToEntity)
                        .collect(Collectors.toSet()))
                .offices(doctor.getOffices().stream()
                        .map(OfficeEntityMapper.INSTANCE::mapToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

  default   DoctorsEntity mapToEntityForPatientCard(Doctors doctor) {
      return DoctorsEntity.builder()
              .doctorId(doctor.getDoctorId())
              .name(doctor.getName())
              .surname(doctor.getSurname())
              .pesel(doctor.getPesel())
              .phone(doctor.getPhone())
              .build();
  }
}