package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.SpecializationDTO;
import pl.clinic.domain.Specialization;

@Mapper(componentModel = "spring")
public interface SpecializationMapper {
    SpecializationMapper INSTANCE = Mappers.getMapper(SpecializationMapper.class);

    default SpecializationDTO mapToDto(Specialization specialization) {
        return SpecializationDTO.builder()
                .specializationId(specialization.getSpecializationId())
                .specializationName(specialization.getSpecializationName())
                .build();
    }

 default   Specialization mapFromDto(SpecializationDTO specializationDTO){
     return Specialization.builder()
             .specializationId(specializationDTO.getSpecializationId())
             .specializationName(specializationDTO.getSpecializationName())
             .build();
    }
}
