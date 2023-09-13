package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Specialization;
import pl.clinic.infrastructure.database.entity.SpecializationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecializationEntityMapper {

    SpecializationEntityMapper INSTANCE = Mappers.getMapper(SpecializationEntityMapper.class);

    @Mapping(target = "doctors", ignore = true)
    Specialization mapFromEntity(SpecializationEntity entity);

    @InheritInverseConfiguration
    SpecializationEntity mapToEntity(Specialization specialization);
}
