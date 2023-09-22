package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.infrastructure.database.entity.AppointmentStatusEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentStatusEntityMapper {
    AppointmentStatusEntityMapper INSTANCE = Mappers.getMapper(AppointmentStatusEntityMapper.class);


    default AppointmentStatus mapFromEntity(AppointmentStatusEntity entity){
        return AppointmentStatus.builder()
                .appointmentStatusId(entity.getAppointmentStatusId())
                .status(entity.getStatus())
                .build();
    }


    default AppointmentStatusEntity mapToEntity(AppointmentStatus newStatus){
        return AppointmentStatusEntity.builder()
                .appointmentStatusId(newStatus.getAppointmentStatusId())
                .status(newStatus.getStatus())
                .build();
    }
}
