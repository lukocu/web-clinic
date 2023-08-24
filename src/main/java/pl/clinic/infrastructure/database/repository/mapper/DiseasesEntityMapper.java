package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.Diseases;
import pl.clinic.infrastructure.database.entity.DiseasesEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiseasesEntityMapper {
    @Mapping(target = "patientsCards", ignore = true)
    Diseases mapFromEntity(DiseasesEntity entity);
}
