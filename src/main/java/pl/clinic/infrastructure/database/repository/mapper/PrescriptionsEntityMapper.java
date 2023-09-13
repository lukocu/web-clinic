package pl.clinic.infrastructure.database.repository.mapper;

import jakarta.persistence.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Prescriptions;
import pl.clinic.infrastructure.database.entity.MedicationsEntity;
import pl.clinic.infrastructure.database.entity.PatientsEntity;
import pl.clinic.infrastructure.database.entity.PrescriptionsEntity;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrescriptionsEntityMapper {

    PrescriptionsEntityMapper INSTANCE = Mappers.getMapper(PrescriptionsEntityMapper.class);

    @Mapping(target = "medications", ignore = true)
    Prescriptions mapFromEntity(PrescriptionsEntity entity);
    default Prescriptions mapFromEntityWithMedications(PrescriptionsEntity entity){
        return mapFromEntity(entity)
                .withMedications(entity.getMedications().stream()
                        .map(MedicationsEntityMapper.INSTANCE::mapFromEntity)
                        .collect(Collectors.toSet()));
    }

   default PrescriptionsEntity mapToEntity(Prescriptions prescription){
        return PrescriptionsEntity.builder()
                .prescriptionDate(prescription.getPrescriptionDate())
               .prescriptionDateEnd(prescription.getPrescriptionDateEnd())
               .prescriptionAvailable(prescription.getPrescriptionAvailable())
               .medications(prescription.getMedications().stream()
                       .map(MedicationsEntityMapper.INSTANCE::mapToEntity)
                       .collect(Collectors.toSet()))
                .build();
   }
}
