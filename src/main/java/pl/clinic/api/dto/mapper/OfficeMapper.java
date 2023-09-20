package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.AppointmentsDTO;
import pl.clinic.api.dto.DoctorDTO;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.domain.Appointments;
import pl.clinic.domain.Doctors;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface OfficeMapper {
    OfficeMapper INSTANCE = Mappers.getMapper(OfficeMapper.class);


    @Mapping(target = "officeDoctorAvailability", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    OfficeDTO mapToDto(Office office);

    default OfficeDTO mapToDtoWithFields(Office office) {
        return OfficeDTO.builder()
                .officeId(office.getOfficeId())
                .firstConsultationFee(office.getFirstConsultationFee())
                .followupConsultationFee(office.getFollowupConsultationFee())
                .doctor(DoctorMapper.INSTANCE.mapToDtoSpecAndOffices(office.getDoctor()))
                .officeDoctorAvailability(office.getOfficeDoctorAvailabilities().stream()
                        .map(OfficeDoctorAvailabilityMapper.INSTANCE::mapToDto)
                        .collect(Collectors.toSet()))
                .appointment(office.getAppointments().stream()
                        .map(AppointmentsMapper.INSTANCE::mapToDtoWithoutOffice)
                        .collect(Collectors.toSet()))
                .build();
    }

    default OfficeDTO mapToDtoWithoutAppointment(Office office) {
        return OfficeDTO.builder()
                .officeId(office.getOfficeId())
                .firstConsultationFee(office.getFirstConsultationFee())
                .followupConsultationFee(office.getFollowupConsultationFee())
                .doctor(DoctorMapper.INSTANCE.mapToDtoSpecAndOffices(office.getDoctor()))
                .officeDoctorAvailability(office.getOfficeDoctorAvailabilities().stream()
                        .map(OfficeDoctorAvailabilityMapper.INSTANCE::mapToDto)
                        .collect(Collectors.toSet()))
                .build();
    }


    default OfficeDTO mapToDtoForDoctor(Office office) {
        return OfficeDTO.builder()
                .officeId(office.getOfficeId())
                .firstConsultationFee(office.getFirstConsultationFee())
                .followupConsultationFee(office.getFollowupConsultationFee())
                .officeDoctorAvailability(office.getOfficeDoctorAvailabilities().stream()
                        .map(OfficeDoctorAvailabilityMapper.INSTANCE::mapToDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    default Office mapFromDtoWithoutAppointment(OfficeDTO office) {
        return Office.builder()
                .officeId(office.getOfficeId())
                .firstConsultationFee(office.getFirstConsultationFee())
                .followupConsultationFee(office.getFollowupConsultationFee())
                .doctor(DoctorMapper.INSTANCE.mapFromDtoSpecAndOffices(office.getDoctor()))
                .officeDoctorAvailabilities(office.getOfficeDoctorAvailability().stream()
                        .map(OfficeDoctorAvailabilityMapper.INSTANCE::mapFromDtoWithoutOffice)
                        .collect(Collectors.toSet()))
                .build();
    }

    default Office mapFromDtoForDoctor(OfficeDTO officeDTO) {
        return Office.builder()
                .officeId(officeDTO.getOfficeId())
                .firstConsultationFee(officeDTO.getFirstConsultationFee())
                .followupConsultationFee(officeDTO.getFollowupConsultationFee())
                .officeDoctorAvailabilities(officeDTO.getOfficeDoctorAvailability().stream()
                        .map(OfficeDoctorAvailabilityMapper.INSTANCE::mapFromDtoWithoutOffice)
                        .collect(Collectors.toSet()))
                .appointments(officeDTO.getAppointment().stream()
                        .map(AppointmentsMapper.INSTANCE::mapFromDtoWithoutOffice)
                        .collect(Collectors.toSet()))
                .build();
    }
}
