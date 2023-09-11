package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.Status;
import pl.clinic.infrastructure.database.entity.AppointmentStatusEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentStatusEntityMapper {
    AppointmentStatusEntityMapper INSTANCE = Mappers.getMapper(AppointmentStatusEntityMapper.class);

    @Mapping(target = "appointments", ignore = true)
    AppointmentStatus mapFromEntity(AppointmentStatusEntity entity);

    @InheritInverseConfiguration
    AppointmentStatusEntity mapToEntity(AppointmentStatus newStatus);
}
