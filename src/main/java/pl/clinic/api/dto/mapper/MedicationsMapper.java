package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.MedicationsDTO;
import pl.clinic.domain.Medications;

@Mapper(componentModel = "spring")
public interface MedicationsMapper {

    MedicationsMapper INSTANCE = Mappers.getMapper(MedicationsMapper.class);

    Medications mapFromDto(MedicationsDTO medication);
}
