package pl.clinic.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.clinic.domain.Appointments;
import pl.clinic.infrastructure.database.entity.AppointmentsEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentsEntityMapper {

    AppointmentsEntityMapper INSTANCE= Mappers.getMapper(AppointmentsEntityMapper.class);

    default  Appointments mapFromEntity(AppointmentsEntity entity){
        return Appointments.builder()
                .appointmentId(entity.getAppointmentId())
                .probableStartTime(entity.getProbableStartTime())
                .actualEndTime(entity.getActualEndTime())
                .appointmentTakenDate(entity.getAppointmentTakenDate())
                .patient(PatientsEntityMapper.INSTANCE.mapFromEntity(entity.getPatient()))
                .office(OfficeEntityMapper.INSTANCE.mapFromEntityWithoutAppointments(entity.getOffice()))
                .appointmentStatus(AppointmentStatusEntityMapper.INSTANCE.mapFromEntity(entity.getAppointmentStatus()))
                .build();
    }

    default AppointmentsEntity mapToEntity(Appointments appointment) {
        return AppointmentsEntity.builder()
                .appointmentId(appointment.getAppointmentId())
                .probableStartTime(appointment.getProbableStartTime())
                .actualEndTime(appointment.getActualEndTime())
                .appointmentTakenDate(appointment.getAppointmentTakenDate())
                .patient(PatientsEntityMapper.INSTANCE.mapToEntity(appointment.getPatient()))
                .office(OfficeEntityMapper.INSTANCE.mapToEntity(appointment.getOffice()))
                .appointmentStatus(AppointmentStatusEntityMapper.INSTANCE.mapToEntity(appointment.getAppointmentStatus()))
                .build();
    }
}

