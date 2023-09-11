package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.Office;
import pl.clinic.domain.Patients;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentsEntityMapper {


    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "office", ignore = true)
    Appointments mapFromEntity(AppointmentsEntity entity);

    default AppointmentsEntity mapToEntity(Appointments appointment) {
        return AppointmentsEntity.builder()
                .probableStartTime(appointment.getProbableStartTime())
                .actualEndTime(appointment.getActualEndTime())
                .appointmentTakenDate(appointment.getAppointmentTakenDate())
                .patient(PatientsEntityMapper.INSTANCE.mapToEntity(appointment.getPatient()))
                .office(OfficeEntityMapper.INSTANCE.mapToEntity(appointment.getOffice()))
                .appointmentStatus(AppointmentStatusEntityMapper.INSTANCE.mapToEntity(appointment.getAppointmentStatus()))
                .build();
    }
}

