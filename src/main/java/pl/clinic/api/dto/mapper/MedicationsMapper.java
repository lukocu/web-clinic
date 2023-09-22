package pl.clinic.api.dto.mapper;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.MedicationsDTO;
import pl.clinic.domain.Medications;
import pl.clinic.infrastructure.database.entity.MedicationsEntity;

@Mapper(componentModel = "spring")
public interface MedicationsMapper {

    MedicationsMapper INSTANCE = Mappers.getMapper(MedicationsMapper.class);


    @Mapping(target = "medicationId", ignore = true)
    Medications mapFromDto(MedicationsDTO medication);

    @InheritInverseConfiguration
    MedicationsDTO mapToDto(Medications medication);
}
