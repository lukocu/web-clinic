package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Diseases;
import pl.clinic.infrastructure.database.entity.DiseasesEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiseasesEntityMapper {
    DiseasesEntityMapper INSTANCE = Mappers.getMapper(DiseasesEntityMapper.class);

    @Mapping(target = "patientCards", ignore = true)
    Diseases mapFromEntity(DiseasesEntity entity);

    @InheritInverseConfiguration
    DiseasesEntity mapToEntity(Diseases diseases);
}
