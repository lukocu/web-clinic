package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.Appointments;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentsEntityMapper {


    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "office", ignore = true)
    Appointments mapFromEntity(AppointmentsEntity entity);

    AppointmentsEntity mapToEntity(Appointments appointment);
}
