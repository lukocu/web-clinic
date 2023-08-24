package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.infrastructure.database.entity.AppointmentStatusEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentStatusEntityMapper {
    @Mapping(target = "appointments",ignore = true)
    AppointmentStatus mapFromEntity(AppointmentStatusEntity entity);

}
