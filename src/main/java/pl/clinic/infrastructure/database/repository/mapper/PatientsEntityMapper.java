package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.entity.PatientsEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientsEntityMapper {

    @Mapping(target = "user",ignore = true)
    @Mapping(target = "appointments",ignore = true)
    Patients mapFromEntity(PatientsEntity entity);
}
