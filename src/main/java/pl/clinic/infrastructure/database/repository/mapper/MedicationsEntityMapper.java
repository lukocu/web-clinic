package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Medications;
import pl.clinic.infrastructure.database.entity.MedicationsEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicationsEntityMapper {

    MedicationsEntityMapper INSTANCE = Mappers.getMapper(MedicationsEntityMapper.class);


  default   Medications mapFromEntity(MedicationsEntity entity){
      return Medications.builder()
              .medicationId(entity.getMedicationId())
              .medicationName(entity.getMedicationName())
              .dosage(entity.getDosage())
              .frequency(entity.getFrequency())
              .build();
  };

    @InheritInverseConfiguration
 default    MedicationsEntity mapToEntity(Medications medications) {
     return    MedicationsEntity.builder()
                .medicationId(medications.getMedicationId())
                .medicationName(medications.getMedicationName())
                .dosage(medications.getDosage())
                .frequency(medications.getFrequency())
                .build();

    }
}
