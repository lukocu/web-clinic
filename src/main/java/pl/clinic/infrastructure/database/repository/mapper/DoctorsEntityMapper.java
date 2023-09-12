package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Doctors;
import pl.clinic.infrastructure.database.entity.DoctorsEntity;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorsEntityMapper {


  DoctorsEntityMapper INSTANCE= Mappers.getMapper(DoctorsEntityMapper.class);

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

 default Doctors mapFromEntityWithAllFields(DoctorsEntity entity){
    return mapFromEntity(entity)
            .withSpecializations(entity.getSpecializations().stream()
                    .map(SpecializationEntityMapper.INSTANCE::mapFromEntity)
                    .collect(Collectors.toSet()))
            .withOffices(entity.getOffices().stream()
                    .map(OfficeEntityMapper.INSTANCE::mapFromEntityWithoutDoctor)
                    .collect(Collectors.toSet()))
            .withPatientCards(entity.getPatientCards().stream()
                    .map(PatientCardEntityMapper.INSTANCE::mapFromEntityWithFields)
                    .collect(Collectors.toSet()));
 }
}