package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.AppointmentsDTO;
import pl.clinic.domain.AppointmentStatus;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.Office;
import pl.clinic.domain.Patients;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AppointmentsMapper {

    AppointmentsMapper INSTANCE = Mappers.getMapper(AppointmentsMapper.class);

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



    default AppointmentsDTO mapToDtoWithoutOffice(Appointments model){
        return AppointmentsDTO.builder()
                .appointmentId(model.getAppointmentId())
                .probableStartTime(model.getProbableStartTime())
                .actualEndTime(model.getActualEndTime())
                .appointmentTakenDate(model.getAppointmentTakenDate())
                .patient(PatientsMapper.INSTANCE.mapToDtoWithoutAppointment(model.getPatient()))
                .appointmentStatus(AppointmentStatusMapper.INSTANCE.mapToDto(model.getAppointmentStatus()))
                .build();
    }

   default Appointments mapFromDtoForPatient(AppointmentsDTO appointmentsDTO) {
       return Appointments.builder()
               .appointmentId(appointmentsDTO.getAppointmentId())
               .probableStartTime(appointmentsDTO.getProbableStartTime())
               .actualEndTime(appointmentsDTO.getActualEndTime())
               .appointmentTakenDate(appointmentsDTO.getAppointmentTakenDate())
               .office(OfficeMapper.INSTANCE.mapFromDtoWithoutAppointment(appointmentsDTO.getOffice()))
               .appointmentStatus(AppointmentStatusMapper.INSTANCE.mapFromDto(appointmentsDTO.getAppointmentStatus()))
               .build();

   }

    default Appointments mapFromDtoWithoutOffice(AppointmentsDTO appointmentsDTO){
        return Appointments.builder()
                .appointmentId(appointmentsDTO.getAppointmentId())
        .probableStartTime(appointmentsDTO.getProbableStartTime())
        .actualEndTime(appointmentsDTO.getActualEndTime())
        .appointmentTakenDate(appointmentsDTO.getAppointmentTakenDate())
        .patient(PatientsMapper.INSTANCE.mapFromDtoWithoutAppointment(appointmentsDTO.getPatient()))
        .appointmentStatus(AppointmentStatusMapper.INSTANCE.mapFromDto(appointmentsDTO.getAppointmentStatus()))
        .build();
    }
}

