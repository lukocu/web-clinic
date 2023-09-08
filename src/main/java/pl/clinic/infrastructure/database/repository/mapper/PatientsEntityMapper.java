package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.entity.PatientsEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientsEntityMapper {

    PatientsEntityMapper INSTANCE = Mappers.getMapper(PatientsEntityMapper.class);
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "patientCard",ignore = true)
    Patients mapFromEntity(PatientsEntity entity);

    @InheritInverseConfiguration
    PatientsEntity mapToEntity(Patients patient);
}
