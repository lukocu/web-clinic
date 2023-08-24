package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.Prescriptions;
import pl.clinic.infrastructure.database.entity.PatientsEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrescriptionsEntityMapper  {

    @Mapping(target = "medications",ignore = true)
    Prescriptions mapFromEntity(PatientsEntity entity);
}
