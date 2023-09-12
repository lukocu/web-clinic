package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.clinic.api.dto.AppointmentStatusDTO;
import pl.clinic.api.dto.AppointmentsDTO;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.api.dto.PatientsDTO;
import pl.clinic.domain.Appointments;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(componentModel = "spring")
public interface AppointmentsMapper {

    default AppointmentsDTO mapToDto(Appointments model) {
        return AppointmentsDTO.builder()
                .appointmentId(model.getAppointmentId())
                .probableStartTime(model.getProbableStartTime())
                .actualEndTime(model.getActualEndTime())
                .appointmentTakenDate(model.getAppointmentTakenDate())
                .patient(PatientsMapper.INSTANCE.mapToDtoWithoutAppointment(model.getPatient()))
                .office(OfficeMapper.INSTANCE.mapToDtoWithoutAppointment(model.getOffice()))
                .appointmentStatus(AppointmentStatusMapper.INSTANCE.mapToDto(model.getAppointmentStatus()))
                .build();
    }
}

