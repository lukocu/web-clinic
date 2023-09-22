package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Specialization;
import pl.clinic.infrastructure.database.entity.SpecializationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecializationEntityMapper {

    SpecializationEntityMapper INSTANCE = Mappers.getMapper(SpecializationEntityMapper.class);


    default Specialization mapFromEntity(SpecializationEntity entity){
        return Specialization.builder()
                .specializationId(entity.getSpecializationId())
                .specializationName(entity.getSpecializationName())
                .build();
    }


    default SpecializationEntity mapToEntity(Specialization specialization){
        return SpecializationEntity.builder()
                .specializationId(specialization.getSpecializationId())
                .specializationName(specialization.getSpecializationName())
                .build();
    }
}
