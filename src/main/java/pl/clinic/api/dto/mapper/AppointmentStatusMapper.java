package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.AppointmentStatusDTO;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Status;

@Mapper(componentModel = "spring")
public interface AppointmentStatusMapper {
    AppointmentStatusMapper INSTANCE = Mappers.getMapper(AppointmentStatusMapper.class);
    default AppointmentStatusDTO mapToDto(AppointmentStatus appointmentStatus) {
      return   AppointmentStatusDTO.builder()
                .appointmentStatusId(appointmentStatus.getAppointmentStatusId())
                .status(String.valueOf(appointmentStatus.getStatus()))
                .build();
    }

   default AppointmentStatus mapFromDto(AppointmentStatusDTO appointmentStatus) {
        return AppointmentStatus.builder()
                .appointmentStatusId(appointmentStatus.getAppointmentStatusId())
                .status(Status.valueOf(appointmentStatus.getStatus()))
                .build();

    }
}
