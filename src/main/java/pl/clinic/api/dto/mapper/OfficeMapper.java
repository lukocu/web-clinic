package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.OfficeDTO;
import pl.clinic.domain.Office;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface OfficeMapper {
    OfficeMapper INSTANCE = Mappers.getMapper(OfficeMapper.class);

    default OfficeDTO mapToDto(Office office) {
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
