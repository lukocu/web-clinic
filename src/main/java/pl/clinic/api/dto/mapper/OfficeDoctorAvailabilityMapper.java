package pl.clinic.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.clinic.api.dto.OfficeDoctorAvailabilityDTO;
import pl.clinic.domain.Office;
import pl.clinic.domain.OfficeDoctorAvailability;

@Mapper(componentModel = "spring")
public interface OfficeDoctorAvailabilityMapper {

    OfficeDoctorAvailabilityMapper INSTANCE = Mappers.getMapper(OfficeDoctorAvailabilityMapper.class);

    default OfficeDoctorAvailabilityDTO mapToDtoWithOfficeId(OfficeDoctorAvailability officeDoctorAvailability) {
        return OfficeDoctorAvailabilityDTO.builder()
                .officeAvailabilityId(officeDoctorAvailability.getOfficeAvailabilityId())
                .date(officeDoctorAvailability.getDate())
                .startTime(officeDoctorAvailability.getStartTime())
                .endTime(officeDoctorAvailability.getEndTime())
                .availabilityStatus(officeDoctorAvailability.getAvailabilityStatus())
                .officeId(officeDoctorAvailability.getOffice().getOfficeId())
                .build();
    }

    default OfficeDoctorAvailabilityDTO mapToDto(OfficeDoctorAvailability officeDoctorAvailability) {
        return OfficeDoctorAvailabilityDTO.builder()
                .officeAvailabilityId(officeDoctorAvailability.getOfficeAvailabilityId())
                .date(officeDoctorAvailability.getDate())
                .startTime(officeDoctorAvailability.getStartTime())
                .endTime(officeDoctorAvailability.getEndTime())
                .availabilityStatus(officeDoctorAvailability.getAvailabilityStatus())
                .officeId(officeDoctorAvailability.getOffice().getOfficeId())
                .build();
    }

    default OfficeDoctorAvailability mapFromDtoWithoutOffice(OfficeDoctorAvailabilityDTO officeDoctorAvailabilityDTO) {
        return OfficeDoctorAvailability.builder()
                .officeAvailabilityId(officeDoctorAvailabilityDTO.getOfficeAvailabilityId())
                .date(officeDoctorAvailabilityDTO.getDate())
                .startTime(officeDoctorAvailabilityDTO.getStartTime())
                .endTime(officeDoctorAvailabilityDTO.getEndTime())
                .availabilityStatus(officeDoctorAvailabilityDTO.getAvailabilityStatus())
                .build();
    }

    default OfficeDoctorAvailability mapFromDtoWithOffice(OfficeDoctorAvailabilityDTO officeDoctorAvailabilityDTO) {
        return OfficeDoctorAvailability.builder()
                .officeAvailabilityId(officeDoctorAvailabilityDTO.getOfficeAvailabilityId())
                .date(officeDoctorAvailabilityDTO.getDate())
                .startTime(officeDoctorAvailabilityDTO.getStartTime())
                .endTime(officeDoctorAvailabilityDTO.getEndTime())
                .availabilityStatus(officeDoctorAvailabilityDTO.getAvailabilityStatus())
                .office(Office.builder()
                        .officeId(officeDoctorAvailabilityDTO.getOfficeId())
                        .build())
                .build();

    }
}
/*
.office(Office.builder()
        .officeId(officeDoctorAvailabilityDTO.getOfficeId())
        .firstConsultationFee(null)
        .followupConsultationFee(null)
        .doctor(null)
        .appointments())
        .build();*/
